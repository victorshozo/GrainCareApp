package usjt.graincare.rest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shozo on 20/10/16.
 */

public class SiloCapacityRest extends BaseCall<Long, Void, Double> {

    @Override
    protected Double doInBackground(Long... params) {
        Call<Double> call;
        call = api.getCapacitySilo(params[0]);

        try {
            Response<Double> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}