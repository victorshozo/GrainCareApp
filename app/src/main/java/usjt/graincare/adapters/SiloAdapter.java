package usjt.graincare.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usjt.graincare.R;
import usjt.graincare.application.MainActivity;
import usjt.graincare.fragments.BeaconsFragment;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.SiloCapacityRest;
import usjt.graincare.rest.SiloPredictionDTO;
import usjt.graincare.rest.SiloPredictionRest;
import usjt.graincare.service.SiloService;
import usjt.graincare.silo.SiloChangedCallback;
import usjt.graincare.util.GrainCareFormatter;
import usjt.graincare.util.GrainDialog;

public class SiloAdapter extends RecyclerView.Adapter<SiloAdapter.ViewHolderSilo> {

    private final SiloService siloService;
    private List<SiloHistory> silos = Collections.emptyList();
    private Context context;
    private Grao grao;
    private Long idSilo;

    public SiloAdapter(List<SiloHistory> silos, Context context) {
        this.silos = silos;
        this.context = context;
        siloService = new SiloService();
    }

    @Override
    public ViewHolderSilo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_silos_details, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolderSilo(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSilo holderSilo, final int position) {
        Silo silo = silos.get(position).getSilo();
        grao = silos.get(position).getGrao();
        idSilo = silo.getId();
        Double capacity = silo.getCapacity();

        holderSilo.icon.setImageResource(R.mipmap.ic_silo);
        holderSilo.id.setText(java.lang.String.format("Silo %s", idSilo + " - "));
        holderSilo.capacity.setText(java.lang.String.format("- %s kg", capacity));
        holderSilo.graoType.setText(grao.getType().getType());
        holderSilo.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getId();
                fragmentJump(idSilo, grao);

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
        @BindView(R.id.silosCardView)
        CardView cv;
        @BindView(R.id.siloIcon)
        ImageView icon;
        @BindView(R.id.siloID)
        TextView id;
        @BindView(R.id.siloGraoType)
        TextView graoType;
        @BindView(R.id.siloCapacity)
        TextView capacity;
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
                    //fragmentJump(idSilo, grao);

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
    }

    @OnClick(R.id.bt_prediction)
    void predictionDialog() {
        try {
            SiloPredictionDTO dto = new SiloPredictionRest().execute(idSilo).get();
            String formattedDate = GrainCareFormatter.from(dto.getPredictionDate());

            GrainDialog.showDialog(context, "Estimativa", "O silo poderá ser aberto em " + formattedDate + ".");
        } catch (ExecutionException | InterruptedException e) {
            GrainDialog.showDialog(context, "Erro", "Você chegou em um erro.");
        }
    }


    @OnClick(R.id.bt_capacity)
    void capacityDialog() {
        try {
            Double cap = new SiloCapacityRest().execute(idSilo).get();
            GrainDialog.showDialog(context, "Volume de Grãos", "O silo está " +
                    new DecimalFormat("##.##").format(cap) + "% cheio.");

        } catch (ExecutionException | InterruptedException e) {
            GrainDialog.showDialog(context, "Erro", "Você chegou em um erro.");
        }
    }

    @OnClick(R.id.bt_open_silo)
    void openSiloDialog() {
        siloService.open(idSilo, new SiloChangedCallback() {

            @Override
            public void success() {
                for (SiloHistory siloHistory : silos) {
                    if (siloHistory.getId() == idSilo) {
                        silos.remove(siloHistory);
                    }
                }
                notifyDataSetChanged();
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

    private void fragmentJump(Long siloId, Grao grao) {
        BeaconsFragment fragment = new BeaconsFragment();
        Bundle args = new Bundle();
        args.putLong("siloId", siloId);
        args.putDouble("graoMaxTemperature", grao.getMaxTemperature());
        fragment.setArguments(args);
        switchContent(fragment);
    }

    private void switchContent(Fragment fragment) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.switchContent(fragment);
        }
    }
}