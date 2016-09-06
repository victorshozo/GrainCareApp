package usjt.graincare.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Grao implements Parcelable {
    private int graoID;
    private String graoTipo;
    private int graoTempMax;

    public Grao(int graoID, String graoTipo, int graoTempMax) {
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

    protected Grao(Parcel in) {
        graoID = in.readInt();
        graoTipo = in.readString();
        graoTempMax = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(graoID);
        dest.writeString(graoTipo);
        dest.writeInt(graoTempMax);
    }

    @SuppressWarnings("unused")
    public static final Creator<Grao> CREATOR = new Creator<Grao>() {
        @Override
        public Grao createFromParcel(Parcel in) {
            return new Grao(in);
        }

        @Override
        public Grao[] newArray(int size) {
            return new Grao[size];
        }
    };
}
