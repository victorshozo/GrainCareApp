package usjt.graincare.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import usjt.graincare.R;
import usjt.graincare.application.MainActivity;
import usjt.graincare.fragments.BeaconsFragment;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.silo_row, parent, false);
        View_Holder_Silo holder_silo = new View_Holder_Silo(view);
        return holder_silo;
    }
    @Override
    public void onBindViewHolder(View_Holder_Silo holder_silo, final int position)
    {
        final int siloID =  silos.get(position).getSiloID();
        final int graoID =  silos.get(position).getGraoID();
        String siloCapacity =  Double.toString(silos.get(position).getSiloCapacity());
        String siloRegion =  silos.get(position).getSiloRegion();
        String siloDataClose =  silos.get(position).getSiloDataClose();
        String siloDataOpen =  silos.get(position).getSiloDataOpen();

        holder_silo.siloIcon.setImageResource(R.mipmap.ic_silo);
        holder_silo.siloID.setText(String.format("ID - %s",siloID));
        holder_silo.siloCapacity.setText(String.format("%s kg",siloCapacity));
        for(Grao grao : graos) {
            if (grao.getGraoID() == (graoID))
            {
                holder_silo.graoTipo.setText(grao.getGraoTipo());
            }
        }
        holder_silo.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentJump(graoID);


/*                                                    //Was beaconActivity
                Intent intent = new Intent(context, BeaconsFragment.class);
                intent.putExtra("siloGraoID",graoID);
                intent.putParcelableArrayListExtra("graos", graos);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //ARRUMAR AQUI NÃO DA PRA INICIAR ACTIVITE PRECISO INICIAR FRAG
                context.(intent);
                //Toast.makeText(context, "SILO: "+ siloID, Toast.LENGTH_SHORT).show();*/
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
        ImageView siloIcon;
        TextView siloID;
        TextView graoTipo;
        TextView siloCapacity;

        View_Holder_Silo(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.silosCardView);
            siloIcon = (ImageView) itemView.findViewById(R.id.siloIcon);
            siloID = (TextView) itemView.findViewById(R.id.siloID);
            siloCapacity= (TextView) itemView.findViewById(R.id.siloCapacidade);
            graoTipo = (TextView) itemView.findViewById(R.id.siloTipoGrao);
        }
    }

    private void fragmentJump(int graoID) {
        BeaconsFragment fragment= new BeaconsFragment();
        Bundle args = new Bundle();
        args.putInt("siloGraoID",graoID);
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