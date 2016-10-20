package usjt.graincare.models;

public class BeaconHistory {
    private Long id;
    private Beacon beacon;
    private SiloHistory siloHistory;
    private Double temperature;
    private Double distance;
    private Double humidity;

    @Deprecated
    public BeaconHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
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