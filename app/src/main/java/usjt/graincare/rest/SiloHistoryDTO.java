package usjt.graincare.rest;

import java.util.List;

import usjt.graincare.models.Beacon;
import usjt.graincare.models.GrainType;

public class SiloHistoryDTO {

	private Long siloId;
	private List<Beacon> beacons;
	private GrainType grainType;

	public SiloHistoryDTO(Long siloId, List<Beacon> beacons,GrainType grainType){
		this.siloId = siloId;
		this.beacons = beacons;
		this.grainType = grainType;
	}

	public Long getSiloId() {
		return siloId;
	}

	public void setSiloId(Long siloId) {
		this.siloId = siloId;
	}

	public List<Beacon> getBeacons() {
		return beacons;
	}

	public void setBeacons(List<Beacon> beacons) {
		this.beacons = beacons;
	}

	public GrainType getGrain() {
		return grainType;
	}

	public void setGrain(GrainType grain) {
		this.grainType = grain;
	}

}