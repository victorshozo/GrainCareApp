package usjt.graincare.rest;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.util.GrainDialog;

public class BeaconHistoryRest extends BaseCall<Long, Void, List<BeaconHistory>> {

    private Long siloID;

    public BeaconHistoryRest(Long siloID){this.siloID = siloID;}

    @Override
    protected List<BeaconHistory> doInBackground(Long... params) {
        //Tirar comentario quando retornar lista de beaconHistory
        Call<List<BeaconHistory>> call = api.listBeaconBySilo(siloID);
        //Call<List<BeaconHistory>> call = api.listBeaconHistory();

        try {
            Response<List<BeaconHistory>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            else
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}