package datenmodelle;

import java.util.*;

/**
 * @author Julia Hemkendreis & Jo Duras
 */
public class Veranstaltung {

    private int id;
    private String unternehmen;
    private String fachrichtung;
    private static Timeslot_Enum fruehesterBeginn = Timeslot_Enum.A;
    private int maxSchueler = 20 ;
    private static int maxVeranstaltungen = 5;
    private int minSchueler = 15;

    public Veranstaltung() {

    }
    public Veranstaltung(int id, String unternehem, String fachrichtung) {
        setId(id);
        setUnternehmen(unternehem);
        setFachrichtung(fachrichtung);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(String unternehmen) {
        this.unternehmen = unternehmen;
    }

    public String getFachrichtung() {
        return fachrichtung;
    }

    public void setFachrichtung(String fachrichtung) {
        this.fachrichtung = fachrichtung;
    }

    public Timeslot_Enum getFruehesterBeginn() {
        return fruehesterBeginn;
    }

    public void setFruehesterBeginn(Timeslot_Enum fruehesterBeginn) {
        this.fruehesterBeginn = fruehesterBeginn;
    }

    public int getMaxSchueler() {
        return maxSchueler;
    }

    public void setMaxSchueler(int maxSchueler) {
        this.maxSchueler = maxSchueler;
    }

    public int getMinSchueler() {
        return minSchueler;
    }

    public void setMinSchueler(int minSchueler) {
        this.minSchueler = minSchueler;
    }
    public int getMaxVeranstaltungen() {
        return maxVeranstaltungen;
    }

    public void setMaxVeranstaltungen(int maxVeranstaltungen) {
        this.maxVeranstaltungen = maxVeranstaltungen;
    }

    /**
     * @author Simon, Jo, Julia
     * @return int
     *
     * Methode ermittelt die maximale Kapazität anhand der maximalen Anzahl an Veranstaltungen
     * und der maximalen Anzahl an Schuelern pro Veranstaltung
     */
    public int ermittleMaxkapazitaet(){
        return this.maxSchueler * this.maxVeranstaltungen;
    }

    /** Erstellt eine Liste an möglichen Zeitpunkten/Slots, wo ein Unternehmen eine Veranstaltung anbieten kann
     * @author Jo, Julia
     * @return List<Timeslot_Enum> TimeSlotListe (Zeitpunkte)
     */
    public static List<Timeslot_Enum> createTimeSlotListe() {
        ArrayList<Timeslot_Enum> timeslots = new ArrayList<>();
        Timeslot_Enum aktuellerSlot = fruehesterBeginn;
        for(int i = 0; i < maxVeranstaltungen; i++){
            timeslots.add(aktuellerSlot);
            switch (aktuellerSlot){
                case A :
                    aktuellerSlot = Timeslot_Enum.B;
                    break;
                case B :
                    aktuellerSlot = Timeslot_Enum.C;
                    break;
                case C :
                    aktuellerSlot = Timeslot_Enum.D;
                    break;
                case D :
                    aktuellerSlot = Timeslot_Enum.E;
                    break;
                case E :
                    break;
            }
        }
        return  timeslots;
    }

    @Override
    public String toString() {
        return "Veranstaltung{" +
                "id=" + id +
                ", unternehmen='" + unternehmen + '\'' +
                ", fachrichtung='" + fachrichtung + '\'' +
                ", fruehesterBeginn=" + fruehesterBeginn +
                ", maxSchueler=" + maxSchueler +
                ", maxVeranstaltungen=" + maxVeranstaltungen +
                ", minSchueler=" + minSchueler +
                '}';
    }
}
