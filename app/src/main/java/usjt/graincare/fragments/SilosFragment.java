package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import usjt.graincare.R;
import usjt.graincare.adapters.SiloAdapter;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;

public class SilosFragment extends Fragment {
   // @BindView (R.id.RecyclerListSilos) RecyclerView recyclerView;

    private View rootView;
    private SiloAdapter adapter;
    private static FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_silos, container, false);
        populateRecycler();
        return rootView;
    }

    private void populateRecycler()
    {
        List<Silo> silos = new ArrayList<>();
        silos.add(new Silo(1,1, 1000.10 ,"10/03/2016", "20/06/2016", "Noroeste"));
        silos.add(new Silo(2,1, 10000.0 ,"10/02/2016", "20/05/2016", "Agreste"));
        silos.add(new Silo(3,3, 100000.0 ,"10/01/2016", "20/04/2016", "Inferno"));
        silos.add(new Silo(4,2, 15000.0 ,"10/04/2016", "20/07/2016", "Satan"));

        ArrayList<Grao> graos = new ArrayList<>();
        graos.add(new Grao(1, "Milho", 30));
        graos.add(new Grao(2, "Soja", 43));
        graos.add(new Grao(3, "Sordo", 56));

        adapter = new SiloAdapter(silos,graos,rootView.getContext());
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerListSilos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public interface raspService {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .build();
    }
}
