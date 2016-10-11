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
import usjt.graincare.adapters.SiloAdapter;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.GraoRest;
import usjt.graincare.rest.SiloRest;

public class SilosFragment extends Fragment {

    boolean silosFechados = false;
    @BindView(R.id.RecyclerListSilos)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_silos, container, false);
        ButterKnife.bind(this, rootView);
        List<SiloHistory> silos = new ArrayList<>();
        ArrayList<Grao> graos = new ArrayList<>();

        try {
            silos = new SiloHistory().execute().get();
            graos = new GraoRest().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        SiloAdapter adapter = new SiloAdapter(silos, graos, rootView.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}