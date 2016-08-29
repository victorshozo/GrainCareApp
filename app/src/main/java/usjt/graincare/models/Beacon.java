package usjt.graincare.models;
public class Beacon {
    private int beaconID;
    private int beaconBatteryIcon;
    private int beaconBattery;
    private long beaconTemperature;
    private int beaconDistance;
    private int siloID;
    public Beacon (int beaconID, int beaconBatteryIcon,int beaconBattery, long beaconTemperature, int siloID)
    {
        this.beaconID = beaconID;
        this.beaconBatteryIcon = beaconBatteryIcon;
        this.beaconBattery = beaconBattery;
        this.beaconTemperature = beaconTemperature;
        this.siloID = siloID;
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
}

