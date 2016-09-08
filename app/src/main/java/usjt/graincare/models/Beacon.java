package usjt.graincare.models;
public class Beacon {
    private int beaconID;
    private int beaconBatteryIcon;
    private int beaconBattery;
    private int beaconHumidity;
    private long beaconTemperature;
    private int beaconDistance;
    private int siloID;

    public Beacon(int beaconID, long beaconTemperature, int beaconHumidity) {
        this.beaconID = beaconID;
        this.beaconTemperature = beaconTemperature;
        this.beaconHumidity = beaconHumidity;
    }

    public int  getBeaconID() {
        return beaconID;
    }

    public int getBeaconBattery() {
        return beaconBattery;
    }

    public long getBeaconTemperature() {
        return beaconTemperature;
    }

    public int getBeaconDistance() {
        return beaconDistance;
    }


    public int getSiloID() {
        return siloID;
    }

    public int getBeaconBatteryIcon() {
        return beaconBatteryIcon;
    }

    public void setBeaconBatteryIcon(int beaconBatteryIcon) {
        this.beaconBatteryIcon = beaconBatteryIcon;
    }

    public void setBeaconDistance(int beaconDistance) {
        this.beaconDistance = beaconDistance;
    }

    public void setSiloID(int siloID) {
        this.siloID = siloID;
    }

    public void setBeaconBattery(int beaconBattery) {
        this.beaconBattery = beaconBattery;
    }

    public void setBeaconID(int beaconID) {
        this.beaconID = beaconID;
    }

    public void setBeaconTemperature(long beaconTemperature) {
        this.beaconTemperature = beaconTemperature;
    }

    public int getBeaconHumidity() {
        return beaconHumidity;
    }

    public void setBeaconHumidity(int beaconHumidity) {
        this.beaconHumidity = beaconHumidity;
    }
}
