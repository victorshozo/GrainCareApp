package usjt.graincare.rest;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import usjt.graincare.models.Beacon;

/**
 * Created by shozo on 21/10/16.
 */

public class BeaconAvailablesRest extends BaseCall<Void, Void, List<Beacon>> {

    @Override
    protected List<Beacon> doInBackground(Void... params) {
        Call<List<Beacon>> call;
        call = api.listBeaconDisponivel();

        try {
            Response<List<Beacon>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}