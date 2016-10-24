package usjt.graincare.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.GrainType;
import usjt.graincare.models.Silo;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.rest.SiloHistoryDTO;
import usjt.graincare.silo.SiloChangedCallback;
import usjt.graincare.util.GrainDialog;

public class SiloService {

    private final GrainCareApi api;

    public SiloService() {
        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    //RETIRADO CAMPO DATA
    /*public void close(Silo silo, List<Beacon> beacons, GrainType grainType, Calendar closed_at, final SiloChangedCallback callback) {
        List<Long> beaconsId = new ArrayList<>();
        for (Beacon beacon : beacons) {
            beaconsId.add(beacon.getId());
        }
 SiloHistoryDTO siloHistoryDTO = new SiloHistoryDTO(silo.getId(), beaconsId, grainType, closed_at);
    */
    public void close(Silo silo, List<Beacon> beacons, GrainType grainType, final SiloChangedCallback callback) {
        List<Long> beaconsId = new ArrayList<>();
        for (Beacon beacon : beacons) {
            beaconsId.add(beacon.getId());
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
