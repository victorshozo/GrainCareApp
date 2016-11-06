package usjt.graincare.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.R;
import usjt.graincare.application.GrainCareSnackBar;
import usjt.graincare.application.MainActivity;
import usjt.graincare.fragments.BeaconsFragment;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.rest.SiloPredictionDTO;
import usjt.graincare.service.SiloService;
import usjt.graincare.silo.SiloChangedCallback;
import usjt.graincare.util.GrainDialog;

public class SiloAdapter extends RecyclerView.Adapter<SiloAdapter.ViewHolderSilo> {

    private final SiloService siloService;
    private List<SiloHistory> silos = Collections.emptyList();
    private Context context;
    private final GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);
    private View view;
    private Long siloId;

    public SiloAdapter(List<SiloHistory> silos, Context context) {
        this.silos = silos;
        this.context = context;
        this.siloService = new SiloService();
    }

    @Override
    public ViewHolderSilo onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_silos_details, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolderSilo(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSilo holderSilo, final int position) {
        Silo silo = silos.get(position).getSilo();
        final Grao grao = silos.get(position).getGrao();
        siloId = silo.getId();
        Double capacity = silo.getCapacity();

        holderSilo.siloId = silo.getId();
        holderSilo.icon.setImageResource(R.mipmap.ic_silo);
        holderSilo.id.setText(java.lang.String.format("Silo %s", siloId + " - "));
        holderSilo.graoType.setText(grao.getType().getType());
        holderSilo.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(siloId, grao);

            }
        });
    }

    @Override
    public int getItemCount() {
        return silos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class ViewHolderSilo extends RecyclerView.ViewHolder {
        Long siloId;

        @BindView(R.id.silosCardView)
        CardView cv;
        @BindView(R.id.siloIcon)
        ImageView icon;
        @BindView(R.id.siloID)
        TextView id;
        @BindView(R.id.siloGraoType)
        TextView graoType;
        @BindView(R.id.iv_arrow)
        ImageView ivArrow;
        @BindView(R.id.lt_swipe)
        SwipeLayout swipeLayout;

        ViewHolderSilo(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            swipeLayout.setLeftSwipeEnabled(false);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, itemView.findViewById(R.id.bottom_wrapper));
            swipeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //changeFragment(idSilo, grao);
                }
            });
            swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
                @Override
                public void onClose(SwipeLayout layout) {
                    ivArrow.setRotation(0);
                }

                @Override
                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                    ivArrow.setRotation(leftOffset / 3);
                }

                @Override
                public void onStartOpen(SwipeLayout layout) {
                }

                @Override
                public void onOpen(SwipeLayout layout) {
                    ivArrow.setRotation(180f);
                }

                @Override
                public void onStartClose(SwipeLayout layout) {
                }

                @Override
                public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                    //when user's hand released.
                }
            });
        }

        @OnClick(R.id.bt_prediction)
        void predictionDialog() {
            api.getPredictionSilo(siloId).enqueue(new Callback<SiloPredictionDTO>() {
                @Override
                public void onResponse(Call<SiloPredictionDTO> call, Response<SiloPredictionDTO> response) {
                    SimpleDateFormat simpleDt = new SimpleDateFormat("dd-MM-yyyy");
                    String format = simpleDt.format(response.body().getPredictionDate().getTime());
                    if (response.isSuccessful()) {
                        GrainDialog.showDialog(context, "Estimativa", "O silo poderá ser aberto em " +
                                format);
                    } else {
                        Integer t = response.code();
                        GrainCareSnackBar.show(view, "Não foi possível obter a previsão.", Snackbar.LENGTH_SHORT);

                    }
                }

                @Override
                public void onFailure(Call<SiloPredictionDTO> call, Throwable t) {
                    GrainCareSnackBar.show(view, "Erro de comunicação com o servidor.", Snackbar.LENGTH_SHORT);
                }
            });
        }

        @OnClick(R.id.bt_capacity)
        void capacityDialog() {
            api.getCapacitySilo(siloId).enqueue(new Callback<Double>() {
                @Override
                public void onResponse(Call<Double> call, Response<Double> response) {
                    if (response.isSuccessful()) {
                        GrainDialog.showDialog(context, "Capacidade", "O silo encontra-se " +
                                response.body() + "% cheio.");
                    } else {
                        GrainCareSnackBar.show(view, "Não foi possível obter a capacidade.", Snackbar.LENGTH_SHORT);
                    }
                }

                @Override
                public void onFailure(Call<Double> call, Throwable t) {
                    GrainCareSnackBar.show(view, "Erro de comunicação com o servidor.", Snackbar.LENGTH_SHORT);
                }
            });
        }

        @OnClick(R.id.bt_open_silo)
        void openSiloDialog() {

            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.ic_question_green_64x64)
                    .setMessage(R.string.abrir_silo)
                    .setTitle("Confirmação")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, int id) {
                            siloService.open(siloId, new SiloChangedCallback() {
                                @Override
                                public void success() {
                                    for (SiloHistory siloHistory : silos) {
                                        if (siloHistory.getSilo().getId() == siloId) {
                                            silos.remove(siloHistory);
                                        }
                                    }

                                    GrainDialog.showDialog(context, "Pronto!", "Silo aberto com sucesso");
                                }

                                @Override
                                public void invalidData() {
                                    GrainDialog.showDialog(context, "Erro", "Silo não encontrado ou ja aberto.");
                                }

                                @Override
                                public void error() {
                                    GrainDialog.showDialog(context, "Erro", "Não foi possível abrir este silo.");
                                }
                            });
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }
    }

    private void changeFragment(Long siloId, Grao grao) {
        BeaconsFragment fragment = new BeaconsFragment();
        Bundle args = new Bundle();
        args.putLong("siloId", siloId);
        args.putDouble("graoMaxTemperature", grao.getMaxTemperature());
        fragment.setArguments(args);
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.switchContent(fragment);
        }
    }

}