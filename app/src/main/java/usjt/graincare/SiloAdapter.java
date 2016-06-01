package usjt.graincare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SiloAdapter extends RecyclerView.Adapter<View_Holder_Silo>{
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
        //Mudar para date???
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

                Intent intent = new Intent(context, BeaconActivity.class);
                intent.putExtra("siloGraoID",graoID);
                intent.putParcelableArrayListExtra("graos", graos);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //Toast.makeText(context, "SILO: "+ siloID, Toast.LENGTH_SHORT).show();
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

    public void insert(int position, Silo silo)
    {
        silos.add(position, silo);
        notifyItemInserted(position);
    }
    public void remove(Silo silo)
    {
        int position = silos.indexOf(silo);
        silos.remove(position);
        notifyItemRemoved(position);
    }
}