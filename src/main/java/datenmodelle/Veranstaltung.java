package datenmodelle;

import java.util.*;

/**
 * @author Julia Hemkendreis & Jo Duras
 */
public class Veranstaltung {
    //Anfang: wird eingelesen
    private int id;
    private String unternehmen;
    private String fachrichtung;
    private Timeslot_Enum fruehesterBeginn = Timeslot_Enum.A;
    private int maxSchueler = 20;
    private int maxVeranstaltungen = 5;
    private int minSchueler = 15;
    //Ende: wird eingelesen
    /**
     * @author Simon, Jan, Jo, Maurice
     * Variable in der die Zuweisung gespeichert werden soll
     */
    private Map<Timeslot_Enum, ArrayList<Schueler>> timeslotReservierung;

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

    public Map<Timeslot_Enum, ArrayList<Schueler>> getTimeslotReservierung() {
        return timeslotReservierung;
    }

    public void setTimeslotReservierung(Map<Timeslot_Enum, ArrayList<Schueler>> timeslotReservierung) {
        this.timeslotReservierung = timeslotReservierung;
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

    /**
     * @author Maurice & Jan & Jo
     * Erstellt eine Map<TimeSlot, ArrayList<Schueler>> die Fruehester beginn und Schueler .
     * Die Map wird genutzt, um die aktuellen Schueler zu Speichern zu diesem TimeSlot.
     * Die Erstellung der Map erfolgt anhand des fruehsten Beginns und der maximalen Anzahl an Veranstaltungen
     */
    public void createTimeSlotMap() {
        timeslotReservierung = new HashMap<>();
        Timeslot_Enum aktuellerSlot = fruehesterBeginn;

        for(int i = 0; i < maxVeranstaltungen; i++){
            timeslotReservierung.put(aktuellerSlot, new ArrayList<>());
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
    }
}
