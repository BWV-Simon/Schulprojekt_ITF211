package datenmodelle;

/**
 * @author Julia Hemkendreis & Jo Duras
 */
public class Veranstaltung {
    private int id;
    private String unternehmen;
    private String fachrichtung;
    private Timeslot_Enum fruehesterBeginn = Timeslot_Enum.A;
    private int maxSchueler = 20;
    private int maxVeranstaltungen = 5;
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

    public int getMaxVeranstaltungen() {
        return maxVeranstaltungen;
    }

    public void setMaxVeranstaltungen(int maxVeranstaltungen) {
        this.maxVeranstaltungen = maxVeranstaltungen;
    }
}
