package usjt.graincare.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import usjt.graincare.R;
import usjt.graincare.application.DrawerInteraction;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.rest.GrainCareRestGenerator;

public class GraphicalReportFragment extends Fragment {


    private final GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_graphic, container, false);
        LineChart chart = (LineChart) rootView.findViewById(R.id.chart);

        List<Entry> temp = new ArrayList<Entry>();
        List<Entry> humi = new ArrayList<Entry>();
        for (int cont = 0; cont < 7; cont++) {
            double randNumber = Math.random();
            float ddd = (float) (randNumber * 100);
            // turn your data into Entry objects
            temp.add(new Entry(cont, ddd));
        }

        for (int cont = 0; cont < 7; cont++) {
            double randNumber = Math.random();
            float ddd = (float) (randNumber * 100);
            // turn your data into Entry objects
            humi.add(new Entry(cont, ddd));
        }


        LineDataSet setTemp = new LineDataSet(temp, "Temperatura (ºC)");
        setTemp.setAxisDependency(YAxis.AxisDependency.LEFT);
        setTemp.setColors(ColorTemplate.rgb("#b30000"));
        setTemp.setCircleColors(ColorTemplate.rgb("#b30000"));
        setTemp.setCircleColorHole(ColorTemplate.rgb("#b30000"));
        setTemp.setValueTypeface(Typeface.MONOSPACE);

        LineDataSet setHum = new LineDataSet(humi, "Humidade (%)");
        setHum.setAxisDependency(YAxis.AxisDependency.LEFT);
        setHum.setColors(ColorTemplate.rgb("#0066ff"));
        setHum.setCircleColors(ColorTemplate.rgb("#0066ff"));
        setHum.setCircleColorHole(ColorTemplate.rgb("#0066ff"));
        setHum.setValueTypeface(Typeface.MONOSPACE);

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setTemp);
        dataSets.add(setHum);


        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.animateX(3000, Easing.EasingOption.EaseInOutSine);
        chart.animateY(3000, Easing.EasingOption.EaseInOutSine);
        chart.setNoDataTextTypeface(Typeface.MONOSPACE);
        chart.setNoDataText("Não há dados para o periodo selecionado");
        chart.setDrawGridBackground(true);
        // the labels that should be drawn on the XAxis
        final String[] quarters = new String[]{"1", "2", "3", "4", "5", "6", "7"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            @Override
            public int getDecimalDigits() {
                return 0;
            }
        };

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.MONOSPACE);
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(Typeface.MONOSPACE);
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(Typeface.MONOSPACE);


        return rootView;
    }
}
