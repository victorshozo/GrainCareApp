package usjt.graincare.models;

public enum GrainType {
    MILHO(44.0, "MILHO"),
    SOJA(30.0, "SOJA"),
    SORGO(30.0, "SORGO");

    private Double maxTemperature;
    private String type;

    GrainType(Double maxTemperature, String type) {
        this.maxTemperature = maxTemperature;
        this.type = type;
    }

    public Double getMaxTemperature() {
        return this.maxTemperature;
    }

    public String getType() {
        return type;
    }
}