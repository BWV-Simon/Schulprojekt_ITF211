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

}

