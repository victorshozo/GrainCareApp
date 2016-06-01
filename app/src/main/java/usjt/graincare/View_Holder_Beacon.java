package usjt.graincare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//TROCAR NOME PARA VIEW_HOLDER_BEACON
public class View_Holder_Beacon extends RecyclerView.ViewHolder {
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