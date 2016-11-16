package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.R;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.rest.GrainCareRestGenerator;
import usjt.graincare.rest.ReportDTO;
import usjt.graincare.util.GrainDialog;

public class SimplifiedReportFragment extends Fragment {

    @BindView(R.id.tv_dt_inicial)
    TextView tvStartDate;
    @BindView(R.id.tv_dt_final)
    TextView tvEndDate;
    @BindView(R.id.tv_farm_name)
    TextView tvFarmName;
    @BindView(R.id.tv_silo)
    TextView tvSiloId;
    @BindView(R.id.tv_total_temperature_average)
    TextView tvTotalTemperatureAverage;
    @BindView(R.id.tv_total_humidity_average)
    TextView tvTotalHumidityAverage;
    @BindView(R.id.tv_total_used_average)
    TextView tvTotalUsedAverage;
    @BindView(R.id.tv_total_profit_average)
    TextView tvProfitAverage;
    @BindView(R.id.tv_total_weight)
    TextView tvTotalWeight;

    private ReportDTO report;
    private final GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);

    public SimplifiedReportFragment(ReportDTO report) {
        this.report = report;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_report_simplified, container, false);
        ButterKnife.bind(this, rootView);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        tvSiloId.setText(report.getSiloId().toString());
        tvFarmName.setText(report.getFarmName());
        tvStartDate.setText(sdf.format(report.getStartDate().getTime()));
        tvEndDate.setText(sdf.format(report.getEndDate().getTime()));
        tvTotalHumidityAverage.setText(report.getTotalAverageHumidity().toString() + "%");
        tvTotalTemperatureAverage.setText(report.getTotalTemperatureAverage().toString() + "ºC");
        tvTotalUsedAverage.setText(report.getTotalPercentUsed().toString() + "%");
        tvProfitAverage.setText("R$" + report.getProfit().toString());
        tvTotalWeight.setText(report.getTotalWeight().toString() + "kg");

        return rootView;
    }

    @OnClick(R.id.btn_email)
    public void sendEmailReport() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar startDate = report.getStartDate();
        Calendar endDate = report.getEndDate();
        String startDateTime = sdf.format(startDate.getTime());
        String endDateTime = sdf.format(endDate.getTime());

        api.sendEmailReport(report.getSiloId(),startDateTime ,endDateTime ).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    GrainDialog.showDialog(getContext(), "Email", "O relatório completo foi enviado para o email do usuário.");
                } else {
                    GrainDialog.showDialog(getContext(), "Email", "Não pudemos nos comunicar com o servidor.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                    GrainDialog.showDialog(getContext(), "INTERNET", "Não pudemos nos comunicar com o servidor.");
            }
        });
    }
}
