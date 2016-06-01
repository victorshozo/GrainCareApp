package usjt.graincare;

import android.content.ClipData;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class View_Holder_Silo extends RecyclerView.ViewHolder {
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