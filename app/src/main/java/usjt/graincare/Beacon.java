package usjt.graincare;
public class Beacon {
    private int beaconID;
    private int beaconBatteryIcon;
    private int beaconBattery;
    private long beaconTemperature;
    private int beaconColor;
    public Beacon (int beaconID, int beaconBatteryIcon,int beaconBattery, long beaconTemperature)
    {
        this.beaconID = beaconID;
        this.beaconBatteryIcon = beaconBatteryIcon;
        this.beaconBattery = beaconBattery;
        this.beaconTemperature = beaconTemperature;
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

    public void setBeaconBatteryIcon(int beaconBatteryIcon) {
        this.beaconBatteryIcon = beaconBatteryIcon;
    }

    public int getBeaconBatteryIcon() {
        return beaconBatteryIcon;
    }
}

