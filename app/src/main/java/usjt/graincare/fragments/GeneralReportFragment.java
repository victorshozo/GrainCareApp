package usjt.graincare.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import usjt.graincare.R;
import usjt.graincare.adapters.BeaconAdapter;
import usjt.graincare.application.GrainCareSnackBar;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.BeaconHistory;
import usjt.graincare.rest.GrainCareRestGenerator;

public class GeneralReportFragment extends Fragment {
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
    @BindView(R.id.tv_total_kilos_average)
    TextView tvTotalKilosAverage;
    @BindView(R.id.tv_total_profit_average)
    TextView tvProfitAverage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_report_response, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
