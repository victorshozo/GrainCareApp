package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import usjt.graincare.R;
import usjt.graincare.adapters.SiloAdapter;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.util.GraoRest;
import usjt.graincare.util.SiloRest;

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

        List<Silo> silos = new ArrayList<>();

        ArrayList<Grao> graos = new ArrayList<>();
        silos.add(new Silo(1L, 1L, 1L, 1000.10D, "10/03/2016", "20/06/2016", "Noroeste"));
        graos.add(new Grao(1L, "Milho", 30D));

        /*try{
            silos= new SiloRest().execute().get();
            graos = new GraoRest().execute().get();
        }catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/
        adapter = new SiloAdapter(silos, graos, rootView.getContext());
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerListSilos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}