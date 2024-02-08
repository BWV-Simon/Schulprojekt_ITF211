package datenmodelle;

/**
 * @author Julia Hemkendreis & Jo Duras
 *
 */
public class Unternehmen {
    private int id;
    private String name;
    private String fachrichtung;
    private Kategorie kategorie;
    private Timeslot timeslot;
    private int maxSchueler = 20;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFachrichtung() {
        return fachrichtung;
    }

    public void setFachrichtung(String fachrichtung) {
        this.fachrichtung = fachrichtung;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public int getMaxSchueler() {
        return maxSchueler;
    }

    public void setMaxSchueler(int maxSchueler) {
        this.maxSchueler = maxSchueler;
    }
}
