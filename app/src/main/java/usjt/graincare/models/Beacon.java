package usjt.graincare.models;
public class Beacon {
    private Long id;
    private Double humidity;
    private Double temperature;
    private Double distance;
    private Long siloID;

    public Beacon(Long id, Long siloID, Double temperature, Double humidity, Double distance) {
        this.id = id;
        this.siloID = siloID;
        this.temperature = temperature;
        this.humidity = humidity;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getDistance() {
        return distance;
    }

    public Long getSiloID() {
        return siloID;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setSiloID(Long siloID) {
        this.siloID = siloID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
