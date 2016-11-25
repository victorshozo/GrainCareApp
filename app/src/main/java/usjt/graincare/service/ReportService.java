package usjt.graincare.service;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Silo;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.rest.ReportDTO;
import usjt.graincare.silo.SiloChangedCallback;
import usjt.graincare.util.GrainDialog;

public class ReportService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private final GrainCareApi api;

    public ReportService() {
        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    public void getReport(Silo silo, final Calendar dtInicial, Calendar dtFinal, final ReportCallback callback) {

        final String startDateTime = sdf.format(dtInicial.getTime());
        String endDateTime = sdf.format(dtFinal.getTime());

        api.getReportSilo(silo.getId(), startDateTime, endDateTime).enqueue(new Callback<ReportDTO>() {
            @Override
            public void onResponse(Call<ReportDTO> call, Response<ReportDTO> response) {
                if (response.isSuccessful()) {
                        response.body().setStartDate(dtInicial);
                        callback.success(response.body());
                } else {
                    callback.invalidData();
                }
            }

            @Override
            public void onFailure(Call<ReportDTO> call, Throwable t) {

                callback.error();
            }
        });
    }


}
