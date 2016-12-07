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
import usjt.graincare.application.MainActivity;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.GrainType;
import usjt.graincare.models.Sensor;
import usjt.graincare.models.Silo;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.service.SiloService;
import usjt.graincare.silo.SiloChangedCallback;
import usjt.graincare.util.GrainDialog;

import static java.util.Arrays.asList;
import static usjt.graincare.models.GrainType.MILHO;
import static usjt.graincare.models.GrainType.SOJA;
import static usjt.graincare.models.GrainType.SORGO;
import static usjt.graincare.util.GrainCareConfig.CLOSE_FLOW;

public class SiloCloseFragment extends Fragment {

    private static final SiloService siloService = new SiloService();
    @BindView(R.id.spinner_silo)
    Spinner spSilo;
    @BindView(R.id.spinner_sensors)
    Spinner spSensors;
    @BindView(R.id.spinner_graos)
    Spinner spGrao;

    final GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);
    View rootView;
    private DrawerInteraction drawerInteraction;
    @Deprecated
    public SiloCloseFragment() {
    }

    public SiloCloseFragment(DrawerInteraction drawerInteraction) {
        this.drawerInteraction = drawerInteraction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_silo_close, container, false);
        ButterKnife.bind(this, rootView);

        listAvailableSilos();
        listAvailableSensors();

        ArrayAdapter<GrainType> adapterGrao = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, asList(MILHO, SOJA, SORGO));
        spGrao.setAdapter(adapterGrao);

        return rootView;
    }

    @OnClick(R.id.bt_confirm_register)
    public void confirmRegister() {

        //Calendar selectedDate = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
        Silo selectedSilo = (Silo) spSilo.getSelectedItem();
        Sensor selectedSensor = (Sensor) spSensors.getSelectedItem();
        GrainType selectedGrainType = (GrainType) spGrao.getSelectedItem();
        List<Sensor> selectedSensors = new ArrayList<>();
        selectedSensors.add(selectedSensor);
        //siloService.close(selectedSilo, selectedSensors, selectedGrainType, selectedDate, new SiloChangedCallback()
        siloService.close(selectedSilo, selectedSensors, selectedGrainType, new SiloChangedCallback() {

            @Override
            public void success() {
                GrainDialog.showDialog(getContext(), "Pronto!", "Silo fechado com sucesso");
                drawerInteraction.changeFragment(new FarmFragment(drawerInteraction), CLOSE_FLOW);
            }

            @Override
            public void invalidData() {
                GrainDialog.showDialog(getContext(), "Dados", "Verifique se os dados foram digitados corretamente.");
            }

            @Override
            public void error() {
                GrainDialog.showDialog(getContext(), "Internet", "Problemas de conexão com o servidor.");
            }
        });
    }

    public void listAvailableSilos() {
        api.listSilosAbertos().enqueue(new Callback<List<Silo>>() {
            @Override
            public void onResponse(Call<List<Silo>> call, Response<List<Silo>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isEmpty()) {
                        GrainDialog.showDialog(getContext(), "Disponibilidade", "Não existem silos disponíveis para o cadastro.");
                        drawerInteraction.changeFragment(new FarmFragment(drawerInteraction),CLOSE_FLOW);
                        return;
                    }

                    ArrayAdapter<Silo> adapterSilos = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, response.body());
                    spSilo.setAdapter(adapterSilos);
                } else {
                    GrainCareSnackBar.show(rootView, "Não foi possivel listar os silos", Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<List<Silo>> call, Throwable t) {
                GrainCareSnackBar.show(rootView, "Problemas de conexão com o servidor.", Snackbar.LENGTH_SHORT);
            }
        });
    }

    public void listAvailableSensors() {
        api.listAvailablesSensors().enqueue(new Callback<List<Sensor>>() {
            @Override
            public void onResponse(Call<List<Sensor>> call, Response<List<Sensor>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isEmpty()) {
                        GrainDialog.showDialog(getContext().getApplicationContext(), "Disponibilidade", "Não existem sensores disponíveis para o cadastro.");
                        drawerInteraction.changeFragment(new FarmFragment(drawerInteraction), CLOSE_FLOW);
                        return;
                    }
                    ArrayAdapter<Sensor> adapterSensor = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, response.body());
                    spSensors.setAdapter(adapterSensor);

                } else {
                    GrainCareSnackBar.show(rootView, "Não foi possivel listar os sensores.", Snackbar.LENGTH_SHORT);

                }
            }

            @Override
            public void onFailure(Call<List<Sensor>> call, Throwable t) {
                GrainCareSnackBar.show(rootView, "Problemas de conexão com o servidor.", Snackbar.LENGTH_SHORT);

            }
        });
    }

}