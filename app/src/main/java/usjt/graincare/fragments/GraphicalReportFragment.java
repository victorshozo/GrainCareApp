package usjt.graincare.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
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

import usjt.graincare.R;
import usjt.graincare.json.GrainCareApi;
import usjt.graincare.models.GraphicDTO;
import usjt.graincare.models.GraphicPointDTO;
import usjt.graincare.rest.GrainCareRestGenerator;

public class GraphicalReportFragment extends Fragment {
    private final GrainCareApi api = GrainCareRestGenerator.create(GrainCareApi.class);
    GraphicDTO report;

    public GraphicalReportFragment(GraphicDTO body) {
        report = body;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_graphic, container, false);
        LineChart chart = (LineChart) rootView.findViewById(R.id.chart);

        List<Entry> entriesTemperatures = new ArrayList<>();
        List<Entry> entriesHumidities = new ArrayList<>();

        for (GraphicPointDTO gpdTemperaturas : report.getTemperatures()) {
            entriesTemperatures.add(new Entry(gpdTemperaturas.getX().floatValue(), gpdTemperaturas.getY().floatValue()));
        }

        //está vindo em branco
        for (GraphicPointDTO gpdHumidities : report.getHumidities()) {
            entriesHumidities.add(new Entry(gpdHumidities.getX().floatValue(), gpdHumidities.getY().floatValue()));
        }

        LineDataSet setTemp = new LineDataSet(entriesTemperatures, "Temperatura (ºC)");
        setTemp.setAxisDependency(YAxis.AxisDependency.LEFT);
        setTemp.setColors(ColorTemplate.rgb("#b30000"));
        setTemp.setCircleColors(ColorTemplate.rgb("#b30000"));
        setTemp.setCircleColorHole(ColorTemplate.rgb("#b30000"));
        setTemp.setLineWidth(4);
        setTemp.setValueTypeface(Typeface.MONOSPACE);

        LineDataSet setHum = new LineDataSet(entriesHumidities, "Humidade (%)");
        setHum.setAxisDependency(YAxis.AxisDependency.LEFT);
        setHum.setColors(ColorTemplate.rgb("#0066ff"));
        setHum.setCircleColors(ColorTemplate.rgb("#0066ff"));
        setHum.setCircleColorHole(ColorTemplate.rgb("#0066ff"));
        setHum.setLineWidth(4);
        setHum.setValueTypeface(Typeface.MONOSPACE);

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setTemp);
        dataSets.add(setHum);


        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.animateX(3000, Easing.EasingOption.Linear);
        chart.animateY(3000, Easing.EasingOption.Linear);
        chart.setNoDataTextTypeface(Typeface.MONOSPACE);
        chart.setNoDataText("Não há dados para o periodo selecionado");
        chart.setDrawGridBackground(true);

        final List<String> numbers = new ArrayList<>();
        for (int i = 0; i < report.getDays(); i++) {
            numbers.add(i+1 + "");
        }
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return numbers.get((int) (value -1));
            }

            // we don't draw numbers, so no decimal digits needed
            @Override
            public int getDecimalDigits() {
                return 0;
            }
        };

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.MONOSPACE);
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(Typeface.MONOSPACE);
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(Typeface.MONOSPACE);

        return rootView;
    }

}

