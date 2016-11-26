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
import usjt.graincare.adapters.SiloAdapter;
import usjt.graincare.application.GrainCareSnackBar;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.GrainCareRestGenerator;

public class SilosFragment extends Fragment {

    @BindView(R.id.RecyclerListSilos)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_silos, container, false);
        ButterKnife.bind(this, rootView);

        GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);

        Bundle bundle = this.getArguments();
        Long farmId = bundle.getLong("farmId");

        api.listSilosHistorybyFarm(farmId).enqueue(new Callback<List<SiloHistory>>() {
            @Override
            public void onResponse(Call<List<SiloHistory>> call, Response<List<SiloHistory>> response) {
                if (response.isSuccessful()) {
                    SiloAdapter adapter = new SiloAdapter(response.body(), getContext());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    return;
                } else {
                    GrainCareSnackBar.show(rootView, "Não foi possivel listar os silos", Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<List<SiloHistory>> call, Throwable t) {
                GrainCareSnackBar.show(rootView, "Sem conexão com o servidor.", Snackbar.LENGTH_SHORT);
            }
        });

        return rootView;
    }
}