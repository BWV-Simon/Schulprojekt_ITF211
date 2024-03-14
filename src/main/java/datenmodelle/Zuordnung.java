package datenmodelle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jo Duras & Julia Hemkendreis
 * Endergebnis des Algorithmus - Struktur der Ausgabe der CSV-Datei
 */
public class Zuordnung {

    private Veranstaltung veranstaltung;
    private Timeslot_Enum zeitpunkt;

    private List<Schueler> schuelerList = new ArrayList<>();
    private int raumNr;
    private int kapazitaet;

    public Zuordnung(Timeslot_Enum zeitpunkt, Veranstaltung veranstaltung) {
        setZeitpunkt(zeitpunkt);
        setVeranstaltung(veranstaltung);
        setKapazitaet(veranstaltung.getMaxSchueler());
    }

    public Veranstaltung getVeranstaltung() {
        return veranstaltung;
    }

    public void setVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }

    public Timeslot_Enum getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(Timeslot_Enum zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    public void setKapazitaet(int kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    public int getRaumNr() {
        return raumNr;
    }

    public void setRaumNr(int raumNr) {
        this.raumNr = raumNr;
    }

    public List<Schueler> getSchuelerList() {
        return schuelerList;
    }
    public void setSchuelerList(List<Schueler> schuelerList) {
        this.schuelerList = schuelerList;
    }

    public void addSchueler(Schueler schueler) {
        if(!schuelerList.contains(schueler)) {
            schuelerList.add(schueler);
        }
    }
}
