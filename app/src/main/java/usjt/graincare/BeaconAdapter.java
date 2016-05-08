package usjt.graincare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public class BeaconAdapter extends RecyclerView.Adapter<View_Holder>{
    List<Beacon> beacons = Collections.emptyList();
    Context context;

    public BeaconAdapter(List<Beacon> beacons, Context context)
    {
        this.beacons = beacons;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //inflate the layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beacon_row, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(View_Holder holder, int position)
    {
        //holder.beacon_id.setText(beacons.get(position).getBeaconID());
        holder.temp.setText(beacons.get(position).getBeaconTemperature());
        holder.bat_icon.setImageResource(beacons.get(position).getBeaconBatteryIcon());
        holder.bat.setText(beacons.get(position).getBeaconBattery());

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
}
