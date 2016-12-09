package usjt.graincare.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
    @BindView(R.id.spinner_graos)
    Spinner spGrao;
    @BindView(R.id.list_sensors)
    TextView tvSensors;

    final List<Integer> listSensors = null;
    List<Integer> list = null;
    final String tvFinal = null;
    final String[] tv_listSensors = null;

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

        ArrayAdapter<GrainType> adapterGrao = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, asList(MILHO, SOJA, SORGO));
        spGrao.setAdapter(adapterGrao);

        return rootView;
    }


    @OnClick(R.id.bt_confirm_register)
    public void confirmRegister() {

        Silo selectedSilo = (Silo) spSilo.getSelectedItem();
        GrainType selectedGrainType = (GrainType) spGrao.getSelectedItem();
        List<Integer> selectedSensors = new ArrayList<>();
        selectedSensors.addAll(list);
        siloService.close(selectedSilo, listSensors, selectedGrainType, new SiloChangedCallback() {

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

    @OnClick(R.id.btn_select_sensors)
    public void sensorsSelectors() {
        fillSensorsList();
        list = null;
    android.app.AlertDialog alert = new AlertDialog.Builder(getActivity())
                .setTitle("Seletor de Silos")
                .setMultiChoiceItems(tv_listSensors, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                            list.add(Integer.valueOf(tv_listSensors[item]));
                            }
                        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        for (int count = 0; count < list.size(); count++) {
                            String tvFinal = new StringBuilder().append(list.get(count)).append(",").toString();
                        }
                        tvSensors.setText(tvFinal);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        list = null;
                        dialog.cancel();
                    }
                })
                .show();
        TextView tvMessage = (TextView) alert.findViewById(android.R.id.message);
        Typeface face = Typeface.createFromAsset(getActivity().getResources().getAssets(), "fonts/museo-sans.ttf");
        tvMessage.setTypeface(face);
        alert.show();
    }

    private void fillSensorsList() {
        api.listAvailablesSensors().enqueue(new Callback<List<Sensor>>() {
            @Override
            public void onResponse(Call<List<Sensor>> call, Response<List<Sensor>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isEmpty()) {
                        GrainDialog.showDialog(getContext().getApplicationContext(), "Disponibilidade", "Não existem sensores disponíveis para o cadastro.");
                        drawerInteraction.changeFragment(new FarmFragment(drawerInteraction), CLOSE_FLOW);
                        return;
                    }
                    for (int count = 0; count < response.body().size(); count++)
                    {
                        tv_listSensors[count] = response.body().get(count).getId().toString();
                    }
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

    //Preencher comboboxes
    public void listAvailableSilos() {
        api.listSilosAbertos().enqueue(new Callback<List<Silo>>() {
            @Override
            public void onResponse(Call<List<Silo>> call, Response<List<Silo>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isEmpty()) {
                        GrainDialog.showDialog(getContext(), "Disponibilidade", "Não existem silos disponíveis para o cadastro.");
                        drawerInteraction.changeFragment(new FarmFragment(drawerInteraction), CLOSE_FLOW);
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

}