package usjt.graincare.rest;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import usjt.graincare.models.Silo;

public class SilosAvailablesRest extends BaseCall<Void, Void, List<Silo>> {

    @Override
    protected List<Silo> doInBackground(Void... params) {
        Call<List<Silo>> call;
        call = api.listSilosAbertos();

        try {
            Response<List<Silo>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}