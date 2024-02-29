package datenmodelle;

import java.util.List;

/**
 * @author Jan Tochtenhagen & Maurice Hennig
 */
public class Schueler {
    private String vorname;
    private String nachname;
    private String klasse;
    private int[] wahl;
    private List<Zuordnung> stundenplan;
    private boolean wuenscheerfuellt=false;
    private int wunschScore=0;

    public Schueler (){

    }
    public Schueler(String vorname, String nachname, String klasse, int[] wahl, List<Zuordnung> stundenplan) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.klasse = klasse;
        this.wahl = wahl;
        this.stundenplan = stundenplan;
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

    public List<Zuordnung> getStundenplan()
    {
        return stundenplan;
    }

    public void setStundenplan(List<Zuordnung> stundenplan)
    {
        this.stundenplan = stundenplan;
    }

    public boolean isWuenscheerfuellt()
    {
        return wuenscheerfuellt;
    }

    public void setWuenscheerfuellt(boolean wuenscheerfuellt)
    {
        this.wuenscheerfuellt = wuenscheerfuellt;
    }

    public int getWunschScore() {
        return wunschScore;
    }

    public void setWunschScore(int wunschScore) {
        this.wunschScore = wunschScore;
    }

    /**
     * @author Maurice Hennig & Jan Tochtenhagen
     */
    public void addScore(int aktScore){
        wunschScore=wunschScore+aktScore;
    }

    public void addToStundenplan(Zuordnung zuordnung) {
        this.stundenplan.add(zuordnung);
    }

    /**
     * @author Jo Duras
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
}
