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
import java.util.concurrent.ExecutionException;

import usjt.graincare.R;
import usjt.graincare.adapters.BeaconAdapter;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.Grao;
import usjt.graincare.util.BeaconRest;

public class BeaconsFragment extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_beacons, container, false);
        StringBuilder temp = new StringBuilder();
        Long graoId = null;
        BeaconRest rest = new BeaconRest();
        List<Beacon> beacons = new ArrayList<>();

        Bundle bundle = this.getArguments();
        Long id = bundle.getLong("siloId");

        try{
            beacons= rest.execute(id).get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayList<Grao> graos = new ArrayList<>();

        if (bundle != null) {
            graoId = bundle.getLong("graoId");
            graos = bundle.getParcelableArrayList("graos");
        }

        for(Grao grao : graos)
        {
            if(grao.getId() == (graoId))
            {
                BeaconAdapter adapter = new BeaconAdapter(beacons, grao.getMaxtemperature(), rootView.getContext());
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