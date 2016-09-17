package usjt.graincare.util;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Beacon;


public class BeaconRest {
    private GrainCareApi api;

    public List<Beacon> doShit(Long siloID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GrainCareConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(GrainCareApi.class);
        final List<Beacon> beacons = Collections.emptyList();
        Call<List<Beacon>> call = api.listBeacons(siloID);
        call.enqueue(new Callback<List<Beacon>>() {

            @Override
            public void onResponse(Call<List<Beacon>> call, Response<List<Beacon>> response) {
                if (response.isSuccessful()) {
                    beacons.addAll(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Beacon>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
        return beacons;
    }
}


  /*  Call<List<Beacon>> call = api.list();
    List<Task>> tasks = call.execute().body();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GrainCareConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    api = retrofit.create(GrainCareApi.class);

    String siloID = siloID;
    Call<List<Beacon>> call = api.listBeacons(siloID);

    try {
        Response<List<Beacon>> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return Collections.emptyList();*/



