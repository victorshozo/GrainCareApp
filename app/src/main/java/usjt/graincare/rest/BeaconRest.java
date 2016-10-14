package usjt.graincare.rest;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import usjt.graincare.models.Beacon;

public class BeaconRest extends BaseCall<Long, Void, ArrayList<Beacon>> {
    @Override
    protected ArrayList<Beacon> doInBackground(Long... params) {
        Call<ArrayList<Beacon>> call = api.listBeacon();

        try {
            Response<ArrayList<Beacon>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}