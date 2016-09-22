package usjt.graincare.util;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Beacon;

public class BeaconRest extends AsyncTask<Long, Void, List<Beacon>> {
    private GrainCareApi api;

    @Override
    protected void onPreExecute() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GrainCareConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(GrainCareApi.class);
    }

    @Override
    protected List<Beacon> doInBackground(Long... params) {
        Call<List<Beacon>> call = api.listBeacons(params[0]);

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