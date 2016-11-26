package usjt.graincare.models;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

import usjt.graincare.models.GrainType;

public class SiloHistoryDTO {
    @SerializedName("siloId")
    private Long siloId;
    @SerializedName("sensorsId")
    private List<Long> sensorsId;
    @SerializedName("grainType")
    private GrainType grainType;
    //@SerializedName("closedAt")
    //private Calendar closedAt;

    public SiloHistoryDTO(Long siloId, List<Long> sensorsId, GrainType grainType){
        this.siloId = siloId;
        this.sensorsId = sensorsId;
        this.grainType = grainType;
    }

    /*public SiloHistoryDTO(Long siloId, List<Long> sensorsId, GrainType grainType, Calendar closedAt){
        this.siloId = siloId;
        this.sensorsId = sensorsId;
        this.grainType = grainType;
        this.closedAt = closedAt;
    }
*/
    public Long getSiloId() {
        return siloId;
    }

    public void setSiloId(Long siloId) {
        this.siloId = siloId;
    }

    public List<Long> getsensorsId() {
        return sensorsId;
    }

    public void setsensorsId(List<Long> sensorsId) {
        this.sensorsId = sensorsId;
    }

    public GrainType getGrainType() {
        return grainType;
    }

    public void setGrainType(GrainType grainType) {
        this.grainType = grainType;
    }

 /*   public Calendar getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Calendar closedAt) {
        this.closedAt = closedAt;
    }*/
}