package datenmodelle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan, Maurice, Jo, Julia & Simon
 */
public class Schueler {
    private String vorname;
    private String nachname;
    private String klasse;
    private int[] wahl;

    private List<Timeslot_Enum> stunden = new ArrayList<>();

    public Schueler() {

    }

    public Schueler(String vorname, String nachname, String klasse, int[] wahl) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.klasse = klasse;
        this.wahl = wahl;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachName) {
        this.nachname = nachName;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public int[] getWahl() {
        return wahl;
    }

    public void setWahl(int[] wahl) {
        this.wahl = wahl;
    }

    public void setWahl(int wahlNr, int wahl) {
        if (wahlNr > 6 || wahlNr < 0) {
            throw new IllegalArgumentException("Die angegebene Nr kann nicht gesetzt werden, da sie zu hoch oder zu niedrig ist!");
        } else if (wahlNr == 6) {
            this.wahl[wahlNr - 1] = wahl;
        } else {
            this.wahl[wahlNr] = wahl;
        }
    }

    public List<Timeslot_Enum> getStunden() {
        return stunden;
    }

    public void setStunden(List<Timeslot_Enum> stunden) {
        this.stunden = stunden;
    }

    public void addStunden(Timeslot_Enum stunde) {
        if (!stunden.contains(stunde)) {
            stunden.add(stunde);
        }
    }

    public void deleteStunde(Timeslot_Enum stunde){
        if(stunden.contains(stunde)){
            stunden.remove(stunde);
        }
    }

    /**
     * @author Jo
     * @return aufbereiteter String zu Testzwecken
     */
    @Override
    public String toString() {
        return
                "Klasse: " + klasse +
                        "; Name: " + nachname +
                        "; Vorname: " + vorname +
                        "; wahl: "
                        + wahl[0] + ","
                        + wahl[1] + ","
                        + wahl[2] + ","
                        + wahl[3] + ","
                        + wahl[4] + ","
                        + wahl[5]
                ;
    }

    /**
     * @author Jo
     * @return aufbereiteter String
     * Gibt die SchuelerInformationen als CSV String aus
     */
    public String toCSVString() {
        return klasse + ";" +
                nachname + ";" +
                vorname + ";";
    }

}
