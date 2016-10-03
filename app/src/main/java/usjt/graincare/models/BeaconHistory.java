package usjt.graincare.models;

public class BeaconHistory {
    private Long id;
    private Beacon beacon;
    private SiloHistory siloHistory;
    private Double temperature;
    private Double distance;
    private Double humidity;

    public BeaconHistory(){
    }

    public BeaconHistory(Long id, Beacon beacon, SiloHistory siloHistory, Double temperature, Double distance, Double humidity) {
        this.id = id;
        this.beacon = beacon;
        this.siloHistory = siloHistory;
        this.temperature = temperature;
        this.distance = distance;
        this.humidity = humidity;
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

    public SiloHistory getSiloHistory() {
        return siloHistory;
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