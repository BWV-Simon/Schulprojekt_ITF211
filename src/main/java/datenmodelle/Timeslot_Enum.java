package datenmodelle;

import java.sql.Time;

/**
 * @author Julia Hemkendreis
 */
public enum Timeslot_Enum {
    A("08:45-09:30"),
    B("09:50-10:35"),
    C("10:35-11:20"),
    D("11:40-12:25"),
    E("12:25-13:10");

    private final String von_bis;
    Timeslot_Enum(String von_bis) {
        this.von_bis = von_bis;
    }

    @Override
    public String toString() {
        return von_bis;
    }

    /** Diese Methode ermittelt den Zeitpunkt/Timeslot über den übergebenen String
     *
     * @param beginn; Beginn des Zeitslots
     * @return Zeitpunkt
     * @throws IllegalArgumentException
     */
    public Timeslot_Enum ermittleTimeslot(String beginn) throws IllegalArgumentException{
        switch (beginn) {
            case "08:45":
                return A;
            case "09:50":
                return B;
            case "10:35":
                return C;
            case "11:40":
                return D;
            case "12:25":
                return E;
            default:
                throw new IllegalArgumentException("Diesen Zeitslot gibt es nicht.");

        }
    }

}

