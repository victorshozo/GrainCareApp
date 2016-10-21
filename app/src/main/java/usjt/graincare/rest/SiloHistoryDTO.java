package usjt.graincare.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import usjt.graincare.models.GrainType;

public class SiloHistoryDTO {

    @SerializedName("siloId")
	private final Long siloId;

    @SerializedName("beaconsId")
    private List<Long> beaconsId;

    @SerializedName("grainType")
    private final String grainType;

    public SiloHistoryDTO(Long siloId, List<Long> beaconsId, GrainType grainType){
		this.siloId = siloId;
        this.beaconsId = beaconsId;
        this.grainType = grainType.name();
    }

	public Long getSiloId() {
		return siloId;
	}

    public List<Long> getBeaconsId() {
        return beaconsId;
    }

    public String getGrainType() {
        return grainType;
    }

}