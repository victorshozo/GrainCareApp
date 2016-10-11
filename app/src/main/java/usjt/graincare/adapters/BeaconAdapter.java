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

import butterknife.BindView;
import butterknife.ButterKnife;
import usjt.graincare.R;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.Grao;

public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.ViewHolderBeacon> {
    List<Beacon> beacons = Collections.emptyList();
    ArrayList<Grao> graos = new ArrayList<>();
    Double maxTemperature;
    Context context;

    public BeaconAdapter(List<Beacon> beacons, Double maxTemperature, Context context) {
        this.beacons = beacons;
        this.context = context;
        this.maxTemperature = maxTemperature;
    }

    @Override
    public ViewHolderBeacon onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_beacons_details, parent, false);
        ViewHolderBeacon holder = new ViewHolderBeacon(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderBeacon holder, int position) {
        Double temperature = beacons.get(position).getTemperature();
        Double humidity = beacons.get(position).getHumidity();
        Double distance = beacons.get(position).getDistance();
        String id = Long.toString(beacons.get(position).getId());

        //Formatar temperatura e definir cor do cardview
        if (temperature > maxTemperature) {
            holder.temperature.setText(String.format(temperature + " Cº"));
            holder.cardView.setCardBackgroundColor(Color.rgb(255, 75, 75));
            holder.temperature.setTypeface(null, Typeface.BOLD);
        } else {
            holder.temperature.setText(String.format("" + temperature + " Cº"));
            holder.temperature.setTypeface(null, Typeface.BOLD);
        }
        holder.distance.setText(String.format(distance + "m"));
        holder.humidity.setText(String.format("%s%%", humidity));
        holder.id.setText(id);
    }

    @Override
    public int getItemCount() {
        return beacons.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class ViewHolderBeacon extends RecyclerView.ViewHolder {
        @BindView(R.id.beaconCardViews) CardView cardView;
        @BindView(R.id.beaconID) TextView id;
        @BindView(R.id.beaconTemperature) TextView temperature;
        @BindView(R.id.beaconHumidity) TextView humidity;
        @BindView(R.id.beaconDistance) TextView distance;

        ViewHolderBeacon(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}