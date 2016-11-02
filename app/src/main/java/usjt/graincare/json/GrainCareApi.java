package usjt.graincare.json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.SiloHistoryDTO;
import usjt.graincare.rest.SiloPredictionDTO;

public interface GrainCareApi {

    @GET("/silos")
    Call<List<Silo>> listSilos( );

    @GET("/silos/history")
    Call<List<SiloHistory>> listSilosHistory( );

    @GET("/silos/history/closed")
    Call<List<SiloHistory>> listSilosHistoryFechados( );

    @GET("/silos/available")
    Call<List<Silo>> listSilosAbertos( );

    @GET("/silo/{siloId}/capacity")
    Call<Double> getCapacitySilo(@Path("siloId") Long siloID);

    @GET("/silo/{siloId}/prediction")
    Call<SiloPredictionDTO> getPredictionSilo(@Path("siloId") Long siloID);

    @POST("/silo/{siloId}/open")
    Call<Void> openSilo(@Path("siloId") Long siloId );

    @POST("/silo/history")
    Call<Void> closeSilo(@Body SiloHistoryDTO body);

    @GET("/beacons/available")
    Call<List<Beacon>> listAvailablesBeacons( );

    @GET("/beacons/silo/{siloHistoryId}")
    Call<List<BeaconHistory>> listBeaconBySilo(@Path("siloHistoryId") Long SiloID );

    /*@POST("/report/silo/{siloId}/")
    Call<ReportDTO> getReportSilo(
            @Path("siloId") Long siloID,
            @Body DateRangeDTO body
    );*/

    @FormUrlEncoded
    @POST("/login")
    Call<Void> login(
            @Field("username") String email,
            @Field("password") String password
    );
}