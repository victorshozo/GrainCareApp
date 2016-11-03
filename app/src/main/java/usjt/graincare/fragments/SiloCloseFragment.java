package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.R;
import usjt.graincare.application.DrawerInteraction;
import usjt.graincare.application.GrainCareSnackBar;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.GrainType;
import usjt.graincare.models.Silo;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.service.SiloService;
import usjt.graincare.silo.SiloChangedCallback;
import usjt.graincare.util.GrainDialog;

import static java.util.Arrays.asList;
import static usjt.graincare.models.GrainType.MILHO;
import static usjt.graincare.models.GrainType.SOJA;

public class SiloCloseFragment extends Fragment {

    private static final SiloService siloService = new SiloService();
    @BindView(R.id.spinner_silo)
    Spinner spSilo;
    @BindView(R.id.spinner_beacons)
    Spinner spBeacon;
    @BindView(R.id.spinner_graos)
    Spinner spGrao;

    final GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);
    View rootView;
    private DrawerInteraction drawerInteraction;

    public SiloCloseFragment(DrawerInteraction drawerInteraction) {
        this.drawerInteraction = drawerInteraction;
    }

   /* @BindView(R.id.new_silo_date_closing)
    DatePicker datePicker;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_silo_add, container, false);
        ButterKnife.bind(this, rootView);

        listAvailableSilos();
        listAvailableBeacons();

        ArrayAdapter<GrainType> adapterGrao = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, asList(MILHO, SOJA));
        spGrao.setAdapter(adapterGrao);

        return rootView;
    }

    @OnClick(R.id.bt_confirm_register)
    public void confirmRegister() {

        //Calendar selectedDate = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
        Silo selectedSilo = (Silo) spSilo.getSelectedItem();
        Beacon selectedBeacon = (Beacon) spBeacon.getSelectedItem();
        GrainType selectedGrainType = (GrainType) spGrao.getSelectedItem();
        List<Beacon> selectedBeacons = new ArrayList<>();
        selectedBeacons.add(selectedBeacon);
        //siloService.close(selectedSilo, selectedBeacons, selectedGrainType, selectedDate, new SiloChangedCallback()
        siloService.close(selectedSilo, selectedBeacons, selectedGrainType, new SiloChangedCallback() {

            @Override
            public void success() {
                GrainDialog.showDialog(getContext(), "Pronto!", "Silo fechado com sucesso");
                drawerInteraction.changeFragment(new SilosFragment());
            }

            @Override
            public void invalidData() {
                GrainDialog.showDialog(getContext(), "Erro", "Erro inválido");
            }

            @Override
            public void error() {
                GrainDialog.showDialog(getContext(), "Erro", "Erro erro.");
            }
        });
    }

    public void listAvailableSilos() {
        api.listSilosAbertos().enqueue(new Callback<List<Silo>>() {
            @Override
            public void onResponse(Call<List<Silo>> call, Response<List<Silo>> response) {
                if (response.isSuccessful()) {
                    ArrayAdapter<Silo> adapterSilos = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, response.body());
                    spSilo.setAdapter(adapterSilos);
                }

                GrainCareSnackBar.show(rootView, "Não foi possivel listar os silos", Snackbar.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<List<Silo>> call, Throwable t) {

            }
        });
    }

    public void listAvailableBeacons() {
        api.listAvailablesBeacons().enqueue(new Callback<List<Beacon>>() {
            @Override
            public void onResponse(Call<List<Beacon>> call, Response<List<Beacon>> response) {
                if (response.isSuccessful()) {
                    ArrayAdapter<Beacon> adapterBeacons = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, response.body());
                    spBeacon.setAdapter(adapterBeacons);

                }

                GrainCareSnackBar.show(rootView, "Não foi possivel listar os beacons", Snackbar.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<List<Beacon>> call, Throwable t) {

            }
        });
    }

}