package datenmodelle;

/**
 * @author Jan Tochtenhagen
 */
public class Schueler {
    private String vorname;
    private String nachname;
    private String klasse;
    private int[] wahl;

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
    public void setWahl(int wahlNr, int wahl){
        if(wahlNr > 6 || wahlNr < 0){
            throw new IllegalArgumentException("Die angegebene Nr kann nicht gesetzt werden, da sie zu hoch oder zu niedrig ist!");
        } else if (wahlNr == 6) {
            this.wahl[wahlNr-1] = wahl;
        } else {
            this.wahl[wahlNr] = wahl;
        }
    }
}
