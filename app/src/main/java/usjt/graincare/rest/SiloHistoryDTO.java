package usjt.graincare.rest;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

import usjt.graincare.models.GrainType;

public class SiloHistoryDTO {
    @SerializedName("siloId")
    private Long siloId;
    @SerializedName("beaconsId")
    private List<Long> beaconsId;
    @SerializedName("grainType")
    private GrainType grainType;
    @SerializedName("closedAt")
    private Calendar closedAt;

    public SiloHistoryDTO(Long siloId, List<Long> beaconsId, GrainType grainType, Calendar closedAt){
        this.siloId = siloId;
        this.beaconsId = beaconsId;
        this.grainType = grainType;
        this.closedAt = closedAt;
    }

    public Long getSiloId() {
        return siloId;
    }

    public void setSiloId(Long siloId) {
        this.siloId = siloId;
    }

    public List<Long> getBeaconsId() {
        return beaconsId;
    }

    public void setBeaconsId(List<Long> beaconsId) {
        this.beaconsId = beaconsId;
    }

    public GrainType getGrainType() {
        return grainType;
    }

    public void setGrainType(GrainType grainType) {
        this.grainType = grainType;
    }

    public Calendar getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Calendar closedAt) {
        this.closedAt = closedAt;
    }
}