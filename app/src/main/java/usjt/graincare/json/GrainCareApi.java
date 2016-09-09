package usjt.graincare.json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import usjt.graincare.models.Silo;

public interface GrainCareApi {

    @GET("/silos")
    Call<List<Silo>> listSilos( );

}