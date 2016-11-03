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

    public void getReport(Silo silo, Calendar dtInicial, Calendar dtFinal, final SiloChangedCallback callback) {

        ReportDTO report = new ReportDTO(dtInicial, dtFinal);
        //TODO - alterar
        api.getReportSilo(silo.getId(), report).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    //TODO O QUE FAZER COM O RESULTADO - provavelmente outro fragment com a visualização dos dados
                } else {
                    callback.invalidData();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.error();
            }
        });
    }


}
