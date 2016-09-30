package usjt.graincare.models;

public class Silo {
    private Long id;
    private Long graoID;
    private Long farmID;
    private Double capacity;
    private String data_close;
    private String data_open;
    private String region;

    public Silo(Long id, Long graoID, Long farmID, Double capacity, String data_close, String data_open, String region)
    {
        this.id = id;
        this.graoID = graoID;
        this.capacity = capacity;
        this.region = region;
        this.data_close = data_close;
        this.data_open = data_open;
        this.farmID = farmID;
    }

    public Long getId() {
        return id;
    }

    public Long getGraoID() {
        return graoID;
    }

    public String getData_close() {
        return data_close;
    }

    public String getData_open() {
        return data_open;
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

    public void setGraoID(Long graoID) {
        this.graoID = graoID;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public void setData_close(String data_close) {
        this.data_close = data_close;
    }

    public void setData_open(String data_open) {
        this.data_open = data_open;
    }

    public Long getFarmID() {
        return farmID;
    }

    public void setFarmID(Long farmID) {
        this.farmID = farmID;
    }
}