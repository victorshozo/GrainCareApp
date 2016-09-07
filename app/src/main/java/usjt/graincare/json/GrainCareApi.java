package usjt.graincare.json;

import java.util.List;

import retrofit2.http.GET;
import usjt.graincare.models.Silo;

public interface GrainCareApi {

    @GET("/silos")
    List<Silo> listSilos();
}
