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
import usjt.graincare.adapters.SiloAdapter;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;

public class SilosFragment extends Fragment {
    // @BindView (R.id.RecyclerListSilos) RecyclerView recyclerView;

    private View rootView;
    private SiloAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_silos, container, false);
        populateRecycler();
        return rootView;
    }

    private void populateRecycler() {
        List<Silo> silosFromApi = new ArrayList<>();

        try {
            silosFromApi = new GrainCareRest().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<Grao> graos = new ArrayList<>();
        graos.add(new Grao(1, "Milho", 30));
        graos.add(new Grao(2, "Soja", 43));
        graos.add(new Grao(3, "Sordo", 56));

        adapter = new SiloAdapter(silosFromApi, graos, rootView.getContext());
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerListSilos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
