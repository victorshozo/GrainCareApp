package usjt.graincare.rest;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Response;
import usjt.graincare.models.PredictionSiloDTO;

public class SiloPredictionRest extends BaseCall<Long, Void, Calendar> {

    @Override
    protected Calendar doInBackground(Long... params) {
        Call<Calendar> call;
        call = api.getPredictionSilo(params[0]);

        try {
            Response<Calendar> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}