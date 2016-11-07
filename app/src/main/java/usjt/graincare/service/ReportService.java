package usjt.graincare.service;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.Silo;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.rest.ReportDTO;
import usjt.graincare.silo.SiloChangedCallback;

public class ReportService {
    private final GrainCareApi api;

    public ReportService() {
        api = GrainCareRestGenerator.create(GrainCareApi.class);
    }

    public void getReport(Silo silo, Calendar dtInicial, Calendar dtFinal, final ReportCallback callback) {

        api.getReportSilo(silo.getId(), dtInicial, dtFinal).enqueue(new Callback<ReportDTO>() {

            @Override
            public void onResponse(Call<ReportDTO> call, Response<ReportDTO> response) {
                if (response.isSuccessful()) {
                    response.body();
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
