package usjt.graincare.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.util.BeaconChangedCallback;

public class BeaconsHistoryService {

    private GrainCareApi api;

    public BeaconsHistoryService() {
        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    /*public beaconsHistoriesBySilo(Long siloId, final BeaconChangedCallback callback) {

        api.listBeaconBySilo(siloId).enqueue(new Callback<List<BeaconHistory>>() {
            @Override
            public void onResponse(Call<List<BeaconHistory>> call, Response<List<BeaconHistory>> response) {
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


