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

    public void setId(Long id) {
        this.id = id;
    }


}
