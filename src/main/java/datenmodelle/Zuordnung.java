package datenmodelle;

import java.util.List;

/**
 * @author Jo Duras & Julia Hemkendreis
 */
public class Zuordnung {
    private List<Schueler> schuelerListe;
    private Veranstaltung unternehmen;
    private Timeslot_Enum zeitpunkt;
    private int raumNr;

    public List<Schueler> getSchuelerListe() {
        return schuelerListe;
    }

    public void setSchuelerListe(List<Schueler> schuelerListe) {
        this.schuelerListe = schuelerListe;
    }

    public Veranstaltung getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(Veranstaltung unternehmen) {
        this.unternehmen = unternehmen;
    }

    public Timeslot_Enum getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(Timeslot_Enum zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public int getRaumNr() {
        return raumNr;
    }

    public void setRaumNr(int raumNr) {
        this.raumNr = raumNr;
    }
}
