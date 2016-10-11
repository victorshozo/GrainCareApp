package usjt.graincare.rest;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Grao;
import usjt.graincare.util.GrainCareConfig;


public class GraoRest extends AsyncTask<Void, Void, ArrayList<Grao>>{

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
    protected ArrayList<Grao> doInBackground(Void... params) {
        Call<ArrayList<Grao>> call = api.listGraos();

        try {
            Response<ArrayList<Grao>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

