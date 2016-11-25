package usjt.graincare.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GraphicDTO {

    @SerializedName("starDate")
    private Calendar startDate;
    @SerializedName("endDate")
    private Calendar endDate;
    @SerializedName("days")
    private Integer days;
    @SerializedName("temperatures")
    private List<GraphicPointDTO> temperatures = new ArrayList<>();
    @SerializedName("humidity")
    private List<GraphicPointDTO> humidities = new ArrayList<>();

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public List<GraphicPointDTO> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<GraphicPointDTO> temperatures) {
        this.temperatures = temperatures;
    }

    public List<GraphicPointDTO> getHumidities() {
        return humidities;
    }

    public void setHumidities(List<GraphicPointDTO> humidities) {
        this.humidities = humidities;
    }
}
