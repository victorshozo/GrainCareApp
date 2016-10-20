package usjt.graincare.rest;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Response;
import usjt.graincare.models.PredictionSiloDTO;

public class SiloPredictionRest extends BaseCall<Long, Void, SiloPredictionDTO> {

    @Override
    protected SiloPredictionDTO doInBackground(Long... params) {
        Call<SiloPredictionDTO> call;
        call = api.getPredictionSilo(params[0]);

        try {
            Response<SiloPredictionDTO> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}