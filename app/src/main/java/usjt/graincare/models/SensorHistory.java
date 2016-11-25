package usjt.graincare.models;

import com.google.gson.annotations.SerializedName;

public class SensorHistory {
    private Long id;
    @SerializedName("sensor")
    private Sensor sensor;
    private SiloHistory siloHistory;
    private Double temperature;
    private Double distance;
    private Double humidity;

    @Deprecated
    public SensorHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public SiloHistory getSiloHistory() {
        return siloHistory;
    }

    public void setSiloHistory(SiloHistory siloHistory) {
        this.siloHistory = siloHistory;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}