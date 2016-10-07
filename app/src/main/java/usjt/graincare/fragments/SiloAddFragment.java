package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import usjt.graincare.R;

public class SiloAddFragment extends Fragment {
    private View rootView;
    private ArrayAdapter<String> adapterSilos;
    private ArrayAdapter<String> adapterBeacons;
    private ArrayAdapter<String> adapterGrao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_silo_add, container, false);

        // create spinner list elements
        adapterSilos = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line);
        adapterSilos.add("Silo 1");
        adapterSilos.add("SIlo 3");
        adapterSilos.add("Sila 4");
        Spinner spn = (Spinner) rootView.findViewById(R.id.spinner_silo);
        spn.setAdapter(adapterSilos);

        adapterBeacons = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line);
        adapterBeacons.add("Beacon 1");
        adapterBeacons.add("Beacon 2");
        adapterBeacons.add("Beacon 3");
        Spinner spnBeacon = (Spinner) rootView.findViewById(R.id.spinner_beacons);
        spnBeacon.setAdapter(adapterBeacons);

        adapterGrao = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line);
        adapterGrao.add("Milho");
        adapterGrao.add("Soja");
        Spinner spnGrao = (Spinner) rootView.findViewById(R.id.spinner_graos);
        spnGrao.setAdapter(adapterGrao);
        return rootView;
    }
}