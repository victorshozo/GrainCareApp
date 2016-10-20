package usjt.graincare.rest;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;

import retrofit2.Converter;
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
                .addConverterFactory(buildGsonConverter())
                .build();
        api = retrofit.create(GrainCareApi.class);
    }

    private Converter.Factory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Adding custom deserializers
        gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }
}
