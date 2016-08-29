package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import usjt.graincare.R;
import usjt.graincare.adapters.BeaconAdapter;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.Grao;

public class BeaconsFragment extends Fragment {
    List<Beacon> beacons = new ArrayList<>();
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_beacons, container, false);
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i <= 13; i++) {
            int bateria = (int) (Math.round(Math.random() * 100));
            long temperatura = Math.round(Math.random() * 100);
            int icon = 0;

            if (bateria >= 65) {
                icon = R.drawable.fullbattery64x64;
            } else if (bateria >= 34 && bateria < 65) {
                icon = R.drawable.middlebattery64x64;
            } else {
                icon = R.drawable.lowbattery64x64;
            }
            beacons.add(new Beacon(i, icon, bateria, temperatura,1));
            //AA4439
        }

        ArrayList<Grao> graos = new ArrayList<>();
        int idGrao = 0;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idGrao = bundle.getInt("siloGraoID");
            graos = bundle.getParcelableArrayList("graos");
        }

        for(Grao grao : graos)
        {
            if(grao.getGraoID() == (idGrao))
            {
                BeaconAdapter adapter = new BeaconAdapter(beacons, grao.getGraoTempMax(), rootView.getContext());
                RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerListBeacons);
                LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        }
        return rootView;
    }
}

