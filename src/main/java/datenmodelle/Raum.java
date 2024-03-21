package datenmodelle;

import java.util.ArrayList;
import java.util.List;

public class Raum {

    private String raumname;

    private int kapzität;

    private List<Timeslot_Enum> zeiten = new ArrayList<>();

    public Raum() {

    }
    public Raum(String s, int x) {
        setRaumname(s);
        setKapzität(x);
    }

    public String getRaumname() {
        return raumname;
    }

    public void setRaumname(String raumname) {
        this.raumname = raumname;
    }

    public int getKapzität() {
        return kapzität;
    }

    public void setKapzität(int kapzität) {
        this.kapzität = kapzität;
    }

    public List<Timeslot_Enum> getZeiten() {
        return zeiten;
    }

    public void setZeiten(List<Timeslot_Enum> zeiten) {
        this.zeiten = zeiten;
    }

    public void addZeitpunkt(Timeslot_Enum zeitpunkt) {
        if (!zeiten.contains(zeitpunkt)) {
            zeiten.add(zeitpunkt);
        }
    }
}
