package usjt.graincare.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import usjt.graincare.models.Silo;

public class SiloRest extends BaseCall<Void, Void, List<Silo>> {

    private boolean searchForOpen = false;

    public SiloRest(boolean searchForOpen) {
        this.searchForOpen = searchForOpen;
    }

    @Override
    protected List<Silo> doInBackground(Void...params) {
        Call<List<Silo>> call;
        if (searchForOpen) {
            call = api.listSilosAbertos();
        } else {
            call = api.listSilos();
        }

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