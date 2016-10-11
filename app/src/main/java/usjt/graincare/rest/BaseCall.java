package usjt.graincare.rest;

import android.os.AsyncTask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.util.GrainCareConfig;

class BaseCall<T, T1, T2> extends AsyncTask<T, T1, T2> {

    GrainCareApi api;

    @Override
    protected T2 doInBackground(T... params) {
        throw new RuntimeException("Must implement");
    }

    @Override
    protected void onPreExecute() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GrainCareConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(GrainCareApi.class);
    }
}
