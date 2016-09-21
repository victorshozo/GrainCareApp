package usjt.graincare.json;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;

public interface GrainCareApi {

    @GET("/silos")
    Call<List<Silo>> listSilos( );

    @GET("/graos")
    Call<ArrayList<Grao>> listGraos( );

 /*   @GET("/silos/{siloID}/beacons")
    Call<List<Beacon>> listBeacons(@Path("siloID") Long siloID);
*/
    @GET("/beacons")
    Call<List<Beacon>> listBeacons();

}