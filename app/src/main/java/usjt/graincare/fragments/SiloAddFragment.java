package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import usjt.graincare.R;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.GrainType;
import usjt.graincare.models.Silo;
import usjt.graincare.rest.BeaconAvailablesRest;
import usjt.graincare.rest.SilosAvailablesRest;
import usjt.graincare.service.SiloService;

import static java.util.Arrays.asList;
import static usjt.graincare.models.GrainType.MILHO;
import static usjt.graincare.models.GrainType.SOJA;

public class SiloAddFragment extends Fragment {

    private View rootView;
    private static final SiloService siloService = new SiloService();

    @BindView(R.id.spinner_silo)
    Spinner spSilo;
    @BindView(R.id.spinner_beacons)
    Spinner spBeacon;
    @BindView(R.id.spinner_graos)
    Spinner spGrao;
    @BindView(R.id.new_silo_date_closing)
    DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_silo_add, container, false);
        ButterKnife.bind(this, rootView);

        List<Silo> silos = Collections.emptyList();

        try {
            silos = new SilosAvailablesRest().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Silo> adapterSilos = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, silos);
        spSilo.setAdapter(adapterSilos);

        List<Beacon> beacons = Collections.emptyList();
        try {
            beacons = new BeaconAvailablesRest().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Beacon> adapterBeacons = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, beacons);
        spBeacon.setAdapter(adapterBeacons);

        ArrayAdapter<GrainType> adapterGrao = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, asList(MILHO, SOJA));
        spGrao.setAdapter(adapterGrao);

        return rootView;
    }

    @OnClick(R.id.bt_confirm_register)
    public void confirmRegister() {

        Calendar selectedDate = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
        Silo selectedSilo = (Silo) spSilo.getSelectedItem();
        Beacon selectedBeacon = (Beacon) spBeacon.getSelectedItem();
        GrainType selectedGrainType = (GrainType) spGrao.getSelectedItem();

        List<Beacon> selectedBeacons = new ArrayList<>();
        selectedBeacons.add(selectedBeacon);

        siloService.close(selectedSilo, selectedBeacons, selectedGrainType, selectedDate);
    }

}