package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import usjt.graincare.R;
import usjt.graincare.adapters.BeaconAdapter;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.util.BeaconRest;
import usjt.graincare.util.GraoRest;
import usjt.graincare.util.SiloRest;

public class BeaconsFragment extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_beacons, container, false);
        StringBuilder temp = new StringBuilder();
        Bundle bundle = this.getArguments();
        BeaconRest rest = new BeaconRest();

        List<Beacon> beacons = rest.doShit(bundle.getLong("siloID"));
        ArrayList<Grao> graos = new ArrayList<>();
        int idGrao = 0;


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
