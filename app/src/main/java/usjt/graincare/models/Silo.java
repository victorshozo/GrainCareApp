package usjt.graincare.models;

public class Silo {

    private Long id;
    private Double capacity;
    private String region;

    public Silo(Long id, Double capacity, String region) {
        this.id = id;
        this.capacity = capacity;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public Double getCapacity() {
        return capacity;
    }

    public String getRegion() {
        return region;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}