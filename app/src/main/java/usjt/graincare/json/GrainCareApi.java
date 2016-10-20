package usjt.graincare.json;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.models.PredictionSiloDTO;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.SiloPredictionDTO;

public interface GrainCareApi {

    @GET("/silos")
    Call<List<Silo>> listSilos( );

    @GET("/silos/history")
    Call<List<SiloHistory>> listSilosHistory( );

    @GET("/silos/abertos")
    Call<List<Silo>> listSilosAbertos( );

    @GET("/silo/{siloId}/capacity")
    Call<Double> getCapacitySilo(@Path("siloId") Long siloID);

    @GET("/silo/{siloId}/prediction")
    Call<SiloPredictionDTO> getPredictionSilo(@Path("siloId") Long siloID);

    @POST("/silos/open/{siloId}")
    Call<Silo> openSilo(@Path("siloId") Long siloID );

    @POST("/silo/history")
    Call<List<Silo>> closeSilo( );

    @GET("/beacon")
    Call<ArrayList<Beacon>> listBeacon( );

    @GET("/beacons/history")
    Call<List<BeaconHistory>> listBeaconHistory( );

    @GET("/beacon/disponivel")
    Call<ArrayList<Beacon>> listBeaconDisponivel( );

    //QUestionar, achei que voltaria uma lista de beaconsHistory para poder verificar temp e etc.
    @GET("/beacons/silo-history/{siloHistoryId}")
    Call<ArrayList<Beacon>> listBeaconBySilo(@Path("siloHistoryId") Long SiloID );

    //neither this...
    @POST("/beacon")
    Call<Beacon> sendBeacon( );

}