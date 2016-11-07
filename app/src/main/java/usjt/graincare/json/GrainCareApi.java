package usjt.graincare.json;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import usjt.graincare.models.Beacon;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.ReportDTO;
import usjt.graincare.rest.SiloHistoryDTO;
import usjt.graincare.rest.SiloPredictionDTO;

public interface GrainCareApi {

    @GET("/silos/history")
    Call<List<SiloHistory>> listSilosHistory( );

    @GET("/silos")
    Call<List<Silo>> listSilos( );

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

    @GET("/beacons/silo/{siloId}")
    Call<List<BeaconHistory>> listBeaconBySilo(@Path("siloId") Long SiloID );

    @POST("/silos/{siloId}/report")
    Call<ReportDTO> getReportSilo(
            @Path("siloId") Long siloID,
            @Query("startDate") Calendar reportStart,
            @Query("endDate") Calendar reportEnd
    );

    @FormUrlEncoded
    @POST("/login")
    Call<Void> login(
            @Field("username") String email,
            @Field("password") String password
    );
}