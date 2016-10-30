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
import usjt.graincare.adapters.BeaconAdapter;
import usjt.graincare.application.GrainCareSnackBar;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.rest.GrainCareRestGenerator;

public class BeaconsFragment extends Fragment {
    @BindView(R.id.RecyclerListBeacons)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_beacons, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();
        final Double graoMaxTemperature = bundle.getDouble("graoMaxTemperature");
        Long siloId = bundle.getLong("siloId");

        GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);

        api.listBeaconBySilo(siloId).enqueue(new Callback<List<BeaconHistory>>() {
            @Override
            public void onResponse(Call<List<BeaconHistory>> call, Response<List<BeaconHistory>> response) {
                if (response.isSuccessful()) {
                    BeaconAdapter adapter = new BeaconAdapter(response.body(), graoMaxTemperature, getContext());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    return;
                }

                GrainCareSnackBar.show(rootView, "Não foi possivel listar os beacons", Snackbar.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<List<BeaconHistory>> call, Throwable t) {
                GrainCareSnackBar.show(rootView, "Não foi possivel listar os beacons", Snackbar.LENGTH_SHORT);
            }
        });
        return rootView;
    }
}