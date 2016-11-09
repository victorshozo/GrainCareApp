package usjt.graincare.service;

import usjt.graincare.json.GrainCareApi;
import usjt.graincare.rest.GrainCareRestGenerator;

public class BeaconsHistoryService {

    private GrainCareApi api;

    public BeaconsHistoryService() {
        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    /*public beaconsHistoriesBySilo(Long siloId, final SensorChangedCallback callback) {

        api.listSensorBySilo(siloId).enqueue(new Callback<List<SensorHistory>>() {
            @Override
            public void onResponse(Call<List<SensorHistory>> call, Response<List<SensorHistory>> response) {
                if (response.isSuccessful()) {
                    return response.body();
                }
                callback.invalidData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.error();
            }
        });
    }
*/
}


