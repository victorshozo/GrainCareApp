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

public class SiloService {

    private final GrainCareApi api;

    public SiloService() {
        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    public void close(Silo silo, List<Beacon> beacons, GrainType grainType, Calendar closed_at) {
        List<Long> beaconsId = new ArrayList<>();
        for (Beacon beacon : beacons) {
            beaconsId.add(beacon.getId());
        }

        SiloHistoryDTO siloHistoryDTO = new SiloHistoryDTO(silo.getId(), beaconsId, grainType);

        api.closeSilo(siloHistoryDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    //TODO FAZ alguma coisa que deu certo..
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //TODO FAZ alguma coisa que deu pau..
            }
        });
    }
}
