package usjt.graincare;

public class Grao {
    private int graoID;
    private String graoTipo;
    private int graoTempMax;

    public Grao(int graoID, String graoTipo, int graoTempMax)
    {
        this.graoID = graoID;
        this.graoTempMax = graoTempMax;
        this.graoTipo = graoTipo;
    }

    public int getGraoID() {
        return graoID;
    }

    public int getGraoTempMax() {
        return graoTempMax;
    }

    public String getGraoTipo() {
        return graoTipo;
    }

    public void setGraoID(int graoID) {
        this.graoID = graoID;
    }

    public void setGraoTempMax(int graoTempMax) {
        this.graoTempMax = graoTempMax;
    }

    public void setGraoTipo(String graoTipo) {
        this.graoTipo = graoTipo;
    }
}
