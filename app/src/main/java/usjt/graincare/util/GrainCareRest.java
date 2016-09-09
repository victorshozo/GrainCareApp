package usjt.graincare.util;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Silo;

public class GrainCareRest  extends AsyncTask<Void, Void, List<Silo>> {

    private GrainCareApi api;

    @Override
    protected void onPreExecute() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GrainCareConfig.BASE_URL)
                .build();
        api = retrofit.create(GrainCareApi.class);
    }

    @Override
    protected List<Silo> doInBackground(Void... params) {
        Call<List<Silo>> call = api.listSilos();

        try {
            Response<List<Silo>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}