package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import usjt.graincare.service.ReportCallback;
import usjt.graincare.service.ReportService;
import usjt.graincare.service.SiloService;
import usjt.graincare.util.GrainDialog;

import static java.util.Arrays.asList;
import static usjt.graincare.models.GrainType.MILHO;
import static usjt.graincare.models.GrainType.SOJA;


public class ReportFragment extends Fragment {


    private static final ReportService reportService = new ReportService();
    @BindView(R.id.spn_relatorio_silos)
    Spinner spnSilos;

    @BindView(R.id.dtPicker_relatorio_inicial)
    DatePicker dtpStart;

    @BindView(R.id.dtPicker_relatorio_final)
    DatePicker dtpEnd;


    final GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);
    private View rootView;
    private DrawerInteraction drawerInteraction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_report, container, false);
        ButterKnife.bind(this, rootView);
        api.listSilos().enqueue(new Callback<List<Silo>>() {
            @Override
            public void onResponse(Call<List<Silo>> call, Response<List<Silo>> response) {
                if (response.isSuccessful()) {
                    ArrayAdapter<Silo> adapterSilos = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, response.body());
                    spnSilos.setAdapter(adapterSilos);
                } else {
                    GrainCareSnackBar.show(rootView, "Não foi possivel listar os silos", Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<List<Silo>> call, Throwable t) {

            }
        });
        ArrayAdapter<GrainType> adapterGrao = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, asList(MILHO, SOJA));
        spnSilos.setAdapter(adapterGrao);

        return rootView;
    }

    @OnClick(R.id.btn_relatorio_geral)
    public void generateReport() {

        Calendar startDate = new GregorianCalendar(dtpStart.getYear(), dtpStart.getMonth() + 1, dtpStart.getDayOfMonth());
        Calendar endDate = new GregorianCalendar(dtpEnd.getYear(), dtpEnd.getMonth() + 1, dtpEnd.getDayOfMonth());
        Silo selectedSilo = (Silo) spnSilos.getSelectedItem();
        reportService.getReport(selectedSilo, startDate, endDate, new ReportCallback() {

            @Override
            public void success() {
                GrainDialog.showDialog(getContext(), "Pronto!", "Silo fechado com sucesso");
                //TODO MUDAR O FRAGMENT
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
}
