package usjt.graincare.models;

public class Sensor {

    private Long id;
    private Boolean available;

    public Sensor(Boolean available) {
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Sensor " + this.id;
    }
}
