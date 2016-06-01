package usjt.graincare;

import java.util.Date;

public class Silo {
    private int siloID;
    private int graoID;
    private double siloCapacity;
    //MUDAR DATAS PARA DATE
    private String siloDataClose;
    private String siloDataOpen;
    private String siloRegion;

    public Silo(int siloID, int graoID, double siloCapacity, String siloDataClose, String siloDataOpen, String siloRegion)
    {
        this.siloID = siloID;
        this.graoID = graoID;
        this.siloCapacity = siloCapacity;
        this.siloRegion = siloRegion;
        this.siloDataClose = siloDataClose;
        this.siloDataOpen = siloDataOpen;
    }

        public int getSiloID() {
            return siloID;
        }

        public int getGraoID() {
            return graoID;
        }

        public String getSiloDataClose() {
            return siloDataClose;
        }

        public String getSiloDataOpen() {
            return siloDataOpen;
        }

        public double getSiloCapacity() {
            return siloCapacity;
        }

        public String getSiloRegion() {
            return siloRegion;
        }

        public void setSiloID(int siloID) {
            this.siloID = siloID;
        }

        public void setGraoID(int graoID) {
            this.graoID = graoID;
        }

        public void setSiloRegion(String siloRegion) {
            this.siloRegion = siloRegion;
        }

        public void setSiloCapacity(double siloCapacity) {
            this.siloCapacity = siloCapacity;
        }

        public void setSiloDataClose(String siloDataClose) {
            this.siloDataClose = siloDataClose;
        }

        public void setSiloDataOpen(String siloDataOpen) {
            this.siloDataOpen = siloDataOpen;
        }
}
