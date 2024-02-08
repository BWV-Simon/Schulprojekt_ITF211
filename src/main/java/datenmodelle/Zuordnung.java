package datenmodelle;

import java.util.List;

/**
 * @author Jo Duras & Julia Hemkendreis
 */
public class Zuordnung {
    private List<Schueler> schuelerListe;
    private Unternehmen unternehmen;
    private Timeslot zeitpunkt;
    private int raumNr;

    public List<Schueler> getSchuelerListe() {
        return schuelerListe;
    }

    public void setSchuelerListe(List<Schueler> schuelerListe) {
        this.schuelerListe = schuelerListe;
    }

    public Unternehmen getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
    }

    public Timeslot getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(Timeslot zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public int getRaumNr() {
        return raumNr;
    }

    public void setRaumNr(int raumNr) {
        this.raumNr = raumNr;
    }
}
