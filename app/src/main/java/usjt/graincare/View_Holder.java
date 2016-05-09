package usjt.graincare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class View_Holder extends RecyclerView.ViewHolder {
    private CardView cv;
    TextView beacon_id;
    TextView temp;
    TextView bat;
    //ImageView icon;
    ImageView bat_icon;

    View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.RecyclerListBeacons);
        beacon_id = (TextView) itemView.findViewById(R.id.beaconID);
        temp = (TextView) itemView.findViewById(R.id.beaconTemperature);
        bat = (TextView) itemView.findViewById(R.id.beaconBattery);
        bat_icon = (ImageView) itemView.findViewById(R.id.beaconBatteryIcon);
        //icon = (ImageView) itemView.findViewById(R.id.beaconImage);
    }
}