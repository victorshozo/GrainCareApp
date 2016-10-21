package usjt.graincare.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import usjt.graincare.models.SiloHistory;

/**
 * Created by shozo on 21/10/16.
 */

public class SiloHistoryClosedRest extends BaseCall<Void, Void, List<SiloHistory>> {

    @Override
    protected List<SiloHistory> doInBackground(Void... params) {
        Call<List<SiloHistory>> call;
        call = api.listSilosHistoryFechados();

        try {
            Response<List<SiloHistory>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}