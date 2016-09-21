package usjt.graincare.models;
public class Beacon {
    private int beaconID;
    private long beaconHumidity;
    private long beaconTemperature;
    private long beaconDistance;
    private int siloID;

    public Beacon(int beaconID, int siloID, long beaconTemperature, long beaconHumidity, long beaconDistance) {
        this.beaconID = beaconID;
        this.siloID = siloID;
        this.beaconTemperature = beaconTemperature;
        this.beaconHumidity = beaconHumidity;
        this.beaconDistance = beaconDistance;
    }

    public int  getBeaconID() {
        return beaconID;
    }

    public long getBeaconTemperature() {
        return beaconTemperature;
    }

    public long getBeaconDistance() {
        return beaconDistance;
    }

    public int getSiloID() {
        return siloID;
    }

    public void setBeaconDistance(long beaconDistance) {
        this.beaconDistance = beaconDistance;
    }

    public void setSiloID(int siloID) {
        this.siloID = siloID;
    }

    public void setBeaconID(int beaconID) {
        this.beaconID = beaconID;
    }

    public void setBeaconTemperature(long beaconTemperature) {
        this.beaconTemperature = beaconTemperature;
    }

    public long getBeaconHumidity() {
        return beaconHumidity;
    }

    public void setBeaconHumidity(long beaconHumidity) {
        this.beaconHumidity = beaconHumidity;
    }
}
