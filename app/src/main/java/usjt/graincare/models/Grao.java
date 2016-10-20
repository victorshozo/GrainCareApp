package usjt.graincare.models;

public class Grao {
	
	private GrainType type;
	private Double maxTemperature;

	public GrainType getType() {
		return type;
	}

	public void setType(GrainType type) {
		this.type = type;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
}