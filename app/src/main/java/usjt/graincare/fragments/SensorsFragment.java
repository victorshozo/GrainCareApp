package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.R;
import usjt.graincare.adapters.SensorAdapter;
import usjt.graincare.application.GrainCareSnackBar;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.SensorHistory;
import usjt.graincare.rest.GrainCareRestGenerator;

public class SensorsFragment extends Fragment {
    @BindView(R.id.RecyclerListBeacons)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_sensors, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();
        final Double graoMaxTemperature = bundle.getDouble("graoMaxTemperature");
        Long siloId = bundle.getLong("siloId");

        GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);

        api.listSensorBySilo(siloId).enqueue(new Callback<List<SensorHistory>>() {
            @Override
            public void onResponse(Call<List<SensorHistory>> call, Response<List<SensorHistory>> response) {
                if (response.isSuccessful()) {
                    SensorAdapter adapter = new SensorAdapter(response.body(), graoMaxTemperature, getContext());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    return;
                } else {
                    GrainCareSnackBar.show(rootView, "Não foi possivel listar os sensores.", Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<List<SensorHistory>> call, Throwable t) {
                GrainCareSnackBar.show(rootView, "Erro de comunição com o servidor.", Snackbar.LENGTH_SHORT);
            }
        });
        return rootView;
    }
}