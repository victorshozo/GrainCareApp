package usjt.graincare.models;

public class Silo {
    private Long id;
    private Double capacity;
    private GrainType region;
    private Double size = 30d;

    @Deprecated
    Silo() {
    }

    public Long getId() {
        return id;
    }

    public Double getCapacity() {
        return capacity;
    }

    public GrainType getRegion() {
        return region;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public void setRegion(GrainType region) {
        this.region = region;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}