package usjt.graincare.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Grao{
    private Long id;
    private GrainType grainType;
    private Double maxTemperature;

    public Grao(Long id, GrainType grainType, Double maxTemperature) {
        this.id = id;
        this.maxTemperature = maxTemperature;
        this.grainType = grainType;
    }


    public Long getId() {
        return id;
    }

    public GrainType getGrainType() {
        return grainType;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGrainType(GrainType grainType) {
        this.grainType = grainType;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

}