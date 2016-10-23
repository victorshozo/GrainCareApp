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

import butterknife.BindView;
import butterknife.ButterKnife;
import usjt.graincare.R;
import usjt.graincare.adapters.BeaconAdapter;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.rest.BeaconHistoryRest;
import usjt.graincare.rest.BeaconRest;
import usjt.graincare.util.GrainDialog;

public class BeaconsFragment extends Fragment {
    @BindView(R.id.RecyclerListBeacons)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beacons, container, false);
        ButterKnife.bind(this, rootView);

        List<BeaconHistory> beacons = new ArrayList<>();

        Bundle bundle = this.getArguments();
        Double graoMaxTemperature = bundle.getDouble("graoMaxTemperature");
        Long siloId = bundle.getLong("siloId");

        try {
            beacons = new BeaconHistoryRest(siloId).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            GrainDialog.showDialog(this.getContext(), "Erro","Deu merda");
            e.printStackTrace();
        }

        BeaconAdapter adapter = new BeaconAdapter(beacons, graoMaxTemperature, rootView.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}