package usjt.graincare.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import usjt.graincare.R;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.Grao;

public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.View_Holder_Beacon>{
    List<Beacon> beacons = Collections.emptyList();
    ArrayList<Grao> graos = new ArrayList<Grao>();
    Double maxTemperature;
    Context context;

    public BeaconAdapter(List<Beacon> beacons, Double maxTemperature, Context context)
    {
        this.beacons = beacons;
        this.context = context;
        this.maxTemperature = maxTemperature;
    }

    @Override
    public View_Holder_Beacon onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //inflate the layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_beacons_details, parent, false);
        View_Holder_Beacon holder = new View_Holder_Beacon(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder_Beacon holder, int position)
    {
        Double temperature =  beacons.get(position).getTemperature();
        Double humidity = beacons.get(position).getHumidity();
        Double distance = beacons.get(position).getDistance();
        String id = Long.toString(beacons.get(position).getId());

        //Formatar temperatura e definir cor do cardview
        if(temperature> maxTemperature)
        {
            holder.temperature.setText(String.format(temperature+" Cº"));
            holder.cardView.setCardBackgroundColor(Color.rgb(255,75,75));
            holder.temperature.setTypeface(null, Typeface.BOLD);
        }
        else
        {
            holder.temperature.setText(String.format(""+temperature+" Cº"));
            holder.temperature.setTypeface(null, Typeface.BOLD);
        }
        holder.distance.setText(String.format(distance+"m"));
        holder.humidity.setText(String.format("%s%%",humidity));
        holder.id.setText(id);
    }

    @Override
    public int getItemCount()
    {
        return beacons.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class View_Holder_Beacon extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView id;
        TextView temperature;
        TextView humidity;
        TextView distance;

        View_Holder_Beacon(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.beaconCardViews);
            id = (TextView) itemView.findViewById(R.id.beaconID);
            temperature = (TextView) itemView.findViewById(R.id.beaconTemperature);
            humidity = (TextView) itemView.findViewById(R.id.beaconHumidity);
            distance = (TextView) itemView.findViewById(R.id.beaconDistance);
        }
    }
}