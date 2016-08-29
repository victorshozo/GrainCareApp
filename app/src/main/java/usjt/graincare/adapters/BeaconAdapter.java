package usjt.graincare.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
import usjt.graincare.models.Beacon;
import usjt.graincare.models.Grao;

public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.View_Holder_Beacon>{
    List<Beacon> beacons = Collections.emptyList();
    ArrayList<Grao> graos = new ArrayList<Grao>();
    int tempMax;
    Context context;

    public BeaconAdapter(List<Beacon> beacons, int tempMax, Context context)
    {
        this.beacons = beacons;
        this.context = context;
        this.tempMax = tempMax;
    }

    @Override
    public View_Holder_Beacon onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //inflate the layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beacon_row, parent, false);
        View_Holder_Beacon holder = new View_Holder_Beacon(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(View_Holder_Beacon holder, int position)
    {
        long temperatura =  beacons.get(position).getBeaconTemperature();
        int bateria = beacons.get(position).getBeaconBattery();
        String id = Integer.toString(beacons.get(position).getBeaconID());


        holder.beacon_id.setText(id);
        if(temperatura> tempMax)
        {

            holder.temp.setText(String.format("Crítico --- %dºC", temperatura));
            holder.cv.setCardBackgroundColor(Color.rgb(255,75,75));
            holder.temp.setTypeface(null, Typeface.BOLD);
        }
        else
        {
            holder.temp.setText(String.format("Estável --- %dºC",temperatura));
            holder.temp.setTypeface(null, Typeface.BOLD);
            //holder.cv.setCardBackgroundColor(Color.rgb(82,226,115));

        }
        holder.bat_icon.setImageResource(beacons.get(position).getBeaconBatteryIcon());
        holder.bat.setTypeface(null, Typeface.BOLD);
        holder.bat.setText(String.format("%3d%%",bateria));
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
    public void insert(int position, Beacon beacon)
    {
        beacons.add(position, beacon);
        notifyItemInserted(position);
    }
    public void remove(Beacon beacon)
    {
        int position = beacons.indexOf(beacon);
        beacons.remove(position);
        notifyItemRemoved(position);
    }

    public static class View_Holder_Beacon extends RecyclerView.ViewHolder {
        CardView cv;
        TextView beacon_id;
        TextView temp;
        TextView bat;
        ImageView bat_icon;

        View_Holder_Beacon(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.beaconCardView);
            beacon_id = (TextView) itemView.findViewById(R.id.beaconID);
            temp = (TextView) itemView.findViewById(R.id.beaconTemperature);
            bat = (TextView) itemView.findViewById(R.id.beaconBattery);
            bat_icon = (ImageView) itemView.findViewById(R.id.beaconBatteryIcon);
        }
    }
}
