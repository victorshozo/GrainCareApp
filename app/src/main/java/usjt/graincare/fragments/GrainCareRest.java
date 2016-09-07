package usjt.graincare.fragments;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Silo;
import usjt.graincare.util.GrainCareConfig;

public class GrainCareRest extends AsyncTask<Void, Void, List<Silo>> {

    private Retrofit retrofit;
    private GrainCareApi api;

    @Override
    protected void onPreExecute() {
        retrofit = new Retrofit.Builder()
                .baseUrl(GrainCareConfig.BASE_URL)
                .build();
    }

    @Override
    protected List<Silo> doInBackground(Void... params) {
        api = retrofit.create(GrainCareApi.class);
        try {
            return api.listSilos();
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
