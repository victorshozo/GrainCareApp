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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import usjt.graincare.R;
import usjt.graincare.models.BeaconHistory;

public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.ViewHolderBeacon> {
    private Double graoMaxTemperature;
    private List<BeaconHistory> beacons;
    private Context context;

    public BeaconAdapter(List<BeaconHistory> beacons, Double graoMaxTemperature, Context context) {
        this.beacons = beacons;
        this.context = context;
        this.graoMaxTemperature = graoMaxTemperature;
    }

    @Override
    public ViewHolderBeacon onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_beacons_details, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolderBeacon(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderBeacon holder, int position) {
        String id = Long.toString(beacons.get(position).getBeacon().getId());
        Double temperature = beacons.get(position).getTemperature();
        Double humidity = beacons.get(position).getHumidity();

        //Formatar temperatura e definir cor do cardview
        if (temperature == null) {
            holder.id.setText(id);
            holder.temperature.setText(String.format(0 + " Cº"));
            holder.humidity.setText(String.format("%s%%", 0));
        } else {
            if (temperature > graoMaxTemperature) {
                holder.id.setText(id);
                holder.temperature.setText(String.format(temperature + " Cº"));
                holder.humidity.setText(String.format("%s%%", humidity));

                //Format cardview to Critical Red temperature
                holder.cardView.setCardBackgroundColor(Color.rgb(255, 75, 75));
            } else {
                holder.id.setText(id);
                holder.temperature.setText(String.format(temperature + " Cº"));
                holder.humidity.setText(String.format("%s%%", humidity));
            }
        }
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
        @BindView(R.id.beaconCardViews)
        CardView cardView;
        @BindView(R.id.beaconID)
        TextView id;
        @BindView(R.id.beaconTemperature)
        TextView temperature;
        @BindView(R.id.beaconHumidity)
        TextView humidity;

        ViewHolderBeacon(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}