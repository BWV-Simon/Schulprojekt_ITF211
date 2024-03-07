package datenmodelle;

import java.util.List;

/**
 * @author Jo Duras & Julia Hemkendreis
 * Endergebnis des Algorithmus - Struktur der Ausgabe der CSV-Datei
 */
public class Zuordnung {

    private Veranstaltung unternehmen;
    private Timeslot_Enum zeitpunkt;
    private int raumNr;

    public Zuordnung() {

    }

    public Zuordnung(Veranstaltung unternehmen, Timeslot_Enum zeitpunkt) {
        this.unternehmen = unternehmen;
        this.zeitpunkt = zeitpunkt;
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
