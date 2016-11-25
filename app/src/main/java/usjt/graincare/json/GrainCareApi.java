package usjt.graincare.json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import usjt.graincare.models.EmailPasswordDTO;
import usjt.graincare.models.GraphicDTO;
import usjt.graincare.models.Sensor;
import usjt.graincare.models.SensorHistory;
import usjt.graincare.models.Silo;
import usjt.graincare.models.SiloHistory;
import usjt.graincare.rest.ReportDTO;
import usjt.graincare.rest.SiloHistoryDTO;
import usjt.graincare.rest.SiloPredictionDTO;

public interface GrainCareApi {

    @GET("/silos")
    Call<List<Silo>> listSilos();

    @GET("/silos/history/closed")
    Call<List<SiloHistory>> listSilosHistoryFechados();

    @GET("/silos/available")
    Call<List<Silo>> listSilosAbertos();

    @GET("/silo/{siloId}/capacity")
    Call<Double> getCapacitySilo(@Path("siloId") Long siloID);

    @GET("/silo/{siloId}/prediction")
    Call<SiloPredictionDTO> getPredictionSilo(@Path("siloId") Long siloID);

    @POST("/silo/{siloId}/open")
    Call<Void> openSilo(@Path("siloId") Long siloId);

    @POST("/silo/history")
    Call<Void> closeSilo(@Body SiloHistoryDTO body);

    @GET("/sensors/available")
    Call<List<Sensor>> listAvailablesSensors();

    @GET("/sensors/silo/{siloId}")
    Call<List<SensorHistory>> listSensorBySilo(@Path("siloId") Long SiloID);

    @GET("/silos/{siloId}/report")
    Call<ReportDTO> getReportSilo(
            @Path("siloId") Long siloID,
            @Query("startDate") String reportStart,
            @Query("endDate") String reportEnd
    );

    @POST("/user/password/reset")
    Call<Void> forgotPassword(
            @Body EmailPasswordDTO email);


    @POST("/silos/{siloId}/report/email")
    Call<Void> sendEmailReport(
            @Path("siloId") Long siloID,
            @Query("startDate") String reportStart,
            @Query("endDate") String reportEnd
    );

    @POST("/logout")
    Call<Void> logout();

    @GET("silos/{siloId}/graphic")
    Call<GraphicDTO> getGraphicData(
            @Path("siloId") Long siloID,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate
    );

    @FormUrlEncoded
    @POST("/login")
    Call<Void> login(
            @Field("username") String email,
            @Field("password") String password
    );
}