package usjt.graincare.json;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.models.Grao;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;

public interface GrainCareApi {

    @GET("/silos")
    Call<List<Silo>> listSilos( );

    @GET("/silos/history")
    Call<List<SiloHistory>> listSilosHistory( );

    @GET("/silos/abertos")
    Call<List<Silo>> listSilosAbertos( );

    @POST("/silo/open/{siloId}")
    Call<Silo> openSilo(@Path("siloId") Long siloID );

    //don't get it...
    @POST("/silo/history")
    Call<List<Silo>> closeSilo( );

    @GET("/graos")
    Call<ArrayList<Grao>> listGraos( );

    @GET("/beacon")
    Call<ArrayList<Beacon>> listBeacon( );

    @GET("/beacon/history")
    Call<ArrayList<BeaconHistory>> listBeaconHistory( );

    @GET("/beacon/disponivel")
    Call<ArrayList<Beacon>> listBeaconDisponivel( );

    @GET("/beacons/silo-history/{siloHistoryId}")
    Call<ArrayList<Beacon>> listBeaconBySilo(@Path("siloHistoryId") Long SiloID );

    //neither this...
    @POST("/beacon")
    Call<Beacon> sendBeacon( );


 /*   @GET("/silos/{siloID}/beacons")
    Call<List<Beacon>> listBeacons(@Path("siloID") Long siloID);
*/

}