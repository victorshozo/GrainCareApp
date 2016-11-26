package usjt.graincare.service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Sensor;
import usjt.graincare.models.GrainType;
import usjt.graincare.models.Silo;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.models.SiloHistoryDTO;
import usjt.graincare.silo.SiloChangedCallback;

public class SiloService {

    private final GrainCareApi api;

    public SiloService() {
        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    public void close(Silo silo, List<Sensor> sensors, GrainType grainType, final SiloChangedCallback callback) {
        List<Long> beaconsId = new ArrayList<>();
        for (Sensor sensor : sensors) {
            beaconsId.add(sensor.getId());
        }

        SiloHistoryDTO siloHistoryDTO = new SiloHistoryDTO(silo.getId(), beaconsId, grainType);
        api.closeSilo(siloHistoryDTO).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.success();
                } else {
                    Integer test = response.code();
                    String tes = response.raw().toString();
                    callback.invalidData();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.error();
            }
        });
    }

    public void open(Long siloId, final SiloChangedCallback callback) {

        api.openSilo(siloId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.success();
                    return;
                }
                callback.invalidData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.error();
            }
        });
    }
}
