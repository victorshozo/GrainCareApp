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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.OnClick;
import usjt.graincare.R;
import usjt.graincare.application.MainActivity;
import usjt.graincare.fragments.BeaconsFragment;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.util.GrainDialog;

public class SiloAdapter extends RecyclerView.Adapter<SiloAdapter.View_Holder_Silo>{
    List<Silo> silos = Collections.emptyList();
    ArrayList<Grao> graos = new ArrayList<Grao>();
    Context context;

    public SiloAdapter(List<Silo> silos, ArrayList<Grao> graos, Context context)
    {
        this.silos = silos;
        this.context = context;
        this.graos = graos;
    }
    @Override
    public View_Holder_Silo onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //inflate the layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_silos_details, parent, false);
        View_Holder_Silo holder_silo = new View_Holder_Silo(view);
        return holder_silo;
    }
    @Override
    public void onBindViewHolder(View_Holder_Silo holder_silo, final int position)
    {
        final Long id =  silos.get(position).getId();
        final Long graoID =  silos.get(position).getGraoID();
        String capacity =  Double.toString(silos.get(position).getCapacity());
        String region =  silos.get(position).getRegion();
        String data_close =  silos.get(position).getData_close();
        String data_open =  silos.get(position).getData_open();

        holder_silo.icon.setImageResource(R.mipmap.ic_silo);
        holder_silo.id.setText(String.format("ID - %s",id));
        holder_silo.capacity.setText(String.format("%s kg",capacity));
        for(Grao grao : graos) {
            if (grao.getId() == (graoID))
            {
                holder_silo.graoType.setText(grao.getType());
            }
        }
        holder_silo.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentJump(graoID, id);

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return silos.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public static class View_Holder_Silo extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView icon;
        TextView id;
        TextView graoType;
        TextView capacity;
        ImageView ivArrow;

        View_Holder_Silo(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.silosCardView);
            icon = (ImageView) itemView.findViewById(R.id.siloIcon);
            id = (TextView) itemView.findViewById(R.id.siloID);
            capacity = (TextView) itemView.findViewById(R.id.siloCapacity);
            graoType = (TextView) itemView.findViewById(R.id.siloGraoType);

            ivArrow = (ImageView) itemView.findViewById(R.id.iv_arrow);
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

    //HELP
    @OnClick(R.id.bt_prediction)
    private void predictionDialog()
    {
        //USAR ENDPOINT /PREDICTION
        GrainDialog.showDialog(context, "Estimativa", "O silo poderá ser aberto em 53 dias.");
    }

    @OnClick(R.id.bt_capacity)
    private void capacityDialog()
    {
        //USAR ENDPOINT /CAPACITY
        GrainDialog.showDialog(context, "Volume de Grãos", "O silo está 86% cheio.");
    }

    private void fragmentJump(Long graoID, Long siloId) {
        BeaconsFragment fragment= new BeaconsFragment();
        Bundle args = new Bundle();
        args.putLong("graoId",graoID);
        args.putLong("siloId",siloId);
        args.putParcelableArrayList("graos", graos);
        fragment.setArguments(args);
        switchContent(fragment);
    }

    public void switchContent(Fragment fragment) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            Fragment frag = fragment;
            mainActivity.switchContent(frag);
        }
    }
}