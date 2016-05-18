package usjt.graincare;

import java.util.Date;

public class Silo {
    private int siloID;
    private int graoID;
    private long siloCapacity;
    private Date siloDataClose;
    private Date siloDataOpen;
    private String regiaoSilo;

    public Silo(int siloID, int graoID, long siloCapacity, Date siloDataClose, Date siloDataOpen, String regiaoSilo)
    {
        this.siloID = siloID;
        this.graoID = graoID;
        this.siloCapacity = siloCapacity;
        this.regiaoSilo = regiaoSilo;
        this.siloDataClose = siloDataClose;
        this.siloDataOpen = siloDataOpen;
    }

        public int getSiloID() {
            return siloID;
        }

        public int getGraoID() {
            return graoID;
        }

        public Date getSiloDataClose() {
            return siloDataClose;
        }

        public Date getSiloDataOpen() {
            return siloDataOpen;
        }

        public long getSiloCapacity() {
            return siloCapacity;
        }

        public String getRegiaoSilo() {
            return regiaoSilo;
        }

        public void setSiloID(int siloID) {
            this.siloID = siloID;
        }

        public void setGraoID(int graoID) {
            this.graoID = graoID;
        }

        public void setRegiaoSilo(String regiaoSilo) {
            this.regiaoSilo = regiaoSilo;
        }

        public void setSiloCapacity(long siloCapacity) {
            this.siloCapacity = siloCapacity;
        }

        public void setSiloDataClose(Date siloDataClose) {
            this.siloDataClose = siloDataClose;
        }

        public void setSiloDataOpen(Date siloDataOpen) {
            this.siloDataOpen = siloDataOpen;
        }
}
