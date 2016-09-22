package usjt.graincare.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Grao implements Parcelable {
    private Long id;
    private String type;
    private Double maxtemperature;

    public Grao(Long id, String type, Double maxtemperature) {
        this.id = id;
        this.maxtemperature = maxtemperature;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMaxtemperature() {
        return maxtemperature;
    }

    public void setMaxtemperature(Double maxtemperature) {
        this.maxtemperature = maxtemperature;
    }

    protected Grao(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readLong();
        type = in.readString();
        maxtemperature = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(id);
        }
        dest.writeString(type);
        if (maxtemperature == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(maxtemperature);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Grao> CREATOR = new Parcelable.Creator<Grao>() {
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