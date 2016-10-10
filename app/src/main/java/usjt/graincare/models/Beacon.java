package usjt.graincare.models;
public class Beacon {
    private Long id;
    private Boolean available;

    public Beacon(Boolean available) {
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

}
