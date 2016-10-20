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

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usjt.graincare.R;
import usjt.graincare.application.MainActivity;
import usjt.graincare.fragments.BeaconsFragment;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.util.GrainDialog;

public class SiloAdapter extends RecyclerView.Adapter<SiloAdapter.ViewHolderSilo> {
    private List<SiloHistory> silos = Collections.emptyList();
    private Context context;

    public SiloAdapter(List<SiloHistory> silos, Context context) {
        this.silos = silos;
        this.context = context;
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
        final Grao grao = silos.get(position).getGrao();
        final Long id = silo.getId();
        Double capacity = silo.getCapacity();

        holderSilo.icon.setImageResource(R.mipmap.ic_silo);
        holderSilo.id.setText(java.lang.String.format("Silo %s", id + " - "));
        holderSilo.capacity.setText(java.lang.String.format("%s kg", capacity));
        holderSilo.graoType.setText(grao.getType().getType());
        holderSilo.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentJump(id, grao);

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

        ViewHolderSilo(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            SwipeLayout swipeLayout = (SwipeLayout) itemView.findViewById(R.id.lt_swipe);
            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            swipeLayout.setLeftSwipeEnabled(false);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, itemView.findViewById(R.id.bottom_wrapper));
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
    public void predictionDialog() {
        //USAR ENDPOINT /PREDICTION
        GrainDialog.showDialog(context, "Estimativa", "O silo poderá ser aberto em 53 dias.");
    }

    @OnClick(R.id.bt_capacity)
    public void capacityDialog() {
        //USAR ENDPOINT /CAPACITY
        GrainDialog.showDialog(context, "Volume de Grãos", "O silo está 86% cheio.");
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