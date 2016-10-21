package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.List;
import java.util.concurrent.ExecutionException;

import usjt.graincare.R;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.Silo;
import usjt.graincare.rest.BeaconAvailablesRest;
import usjt.graincare.rest.SiloHistoryPostRest;
import usjt.graincare.rest.SilosAvailablesRest;

public class SiloAddFragment extends Fragment {
    private View rootView;
    private ArrayAdapter<String> adapterSilos;
    private ArrayAdapter<String> adapterBeacons;
    private ArrayAdapter<String> adapterGrao;
    Button btn;
    List<Silo> silos;
    List<Beacon> beacons;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_silo_add, container, false);
        // create spinner list elements
        adapterSilos = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item);
        try {
            silos = new SilosAvailablesRest().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        for (Silo silo : silos) {
            adapterSilos.add("Silo " + silo.getId());
            adapterSilos.notifyDataSetChanged();
        }
        Spinner spn = (Spinner) rootView.findViewById(R.id.spinner_silo);
        spn.setAdapter(adapterSilos);


        adapterBeacons = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        try {
            beacons = new BeaconAvailablesRest().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        for (Beacon beacon : beacons) {
            adapterBeacons.add("Beacon " + beacon.getId());
            adapterBeacons.notifyDataSetChanged();
        }
        Spinner spnB = (Spinner) rootView.findViewById(R.id.spinner_beacons);
        spnB.setAdapter(adapterBeacons);

        adapterGrao = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line);
        adapterGrao.add("Milho");
        adapterGrao.add("Soja");
        Spinner spnGrao = (Spinner) rootView.findViewById(R.id.spinner_graos);
        spnGrao.setAdapter(adapterGrao);

        DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.new_silo_date_closing);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        //datePickerDialog.show(suppor, "date_picker");
       btn = (Button) rootView.findViewById(R.id.btnCadSilo);

        return rootView;
    }

}