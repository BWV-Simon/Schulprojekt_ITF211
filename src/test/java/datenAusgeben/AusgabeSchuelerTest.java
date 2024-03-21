package datenAusgeben;

import datenmodelle.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jo Duras
 */
public class AusgabeSchuelerTest {

    /**
     * Testet die GetVeranstalktungFuerSchueler Methode
     */
    @Test
    public void getVeranstaltungFuerSchuelerTest() {
        //Given
        int[] wahl = {1,2,3,4,5,6};
        Schueler s1 = new Schueler("Max", "Mustermann", "ITF211", wahl);
        Veranstaltung v1 = new Veranstaltung();
        v1.setId(1);
        v1.setUnternehmen("Muster1");
        v1.setFachrichtung("IT");
        Veranstaltung v2 = new Veranstaltung();
        v2.setId(1);
        v2.setUnternehmen("Muster2");
        v2.setFachrichtung("Wirtschaft");
        Zuordnung z1 = new Zuordnung(Timeslot_Enum.A, v1);
        Zuordnung z2 = new Zuordnung(Timeslot_Enum.B, v1);
        Zuordnung z3 = new Zuordnung(Timeslot_Enum.C, v2);

        z2.addSchueler(s1);
        z3.addSchueler(s1);
        z1.setRaumNr(new Raum("abc", 20));
        z2.setRaumNr(new Raum("efg", 20));
        z3.setRaumNr(new Raum("hij", 20));
        List<Zuordnung> zuordnungen = new ArrayList<>();
        zuordnungen.add(z1);
        zuordnungen.add(z2);
        zuordnungen.add(z3);

        //When
        List<Zuordnung> testErgebnis = AusgabeSchueler.getVeranstaltungFuerSchueler(s1, zuordnungen);

        //Then
        assertTrue(testErgebnis.size() == 2);

    }

    @Test
    public void erstelleListeFuerAusgabeTest() {
        //Given
        int[] wahl = {1,2,3,4,5,6};
        Schueler s1 = new Schueler("Max", "Mustermann", "ITF211", wahl);
        Schueler s2 = new Schueler("Maxi", "Musterfrau", "ITF211", wahl);
        List<Schueler> schuelerListe = new ArrayList<>();
        schuelerListe.add(s1);
        schuelerListe.add(s2);

        Veranstaltung v1 = new Veranstaltung();
        v1.setId(1);
        v1.setUnternehmen("Muster1");
        v1.setFachrichtung("IT");
        Veranstaltung v2 = new Veranstaltung();
        v2.setId(1);
        v2.setUnternehmen("Muster2");
        v2.setFachrichtung("Wirtschaft");
        Zuordnung z1 = new Zuordnung(Timeslot_Enum.A, v1);
        Zuordnung z2 = new Zuordnung(Timeslot_Enum.B, v1);
        Zuordnung z3 = new Zuordnung(Timeslot_Enum.C, v2);
        z2.addSchueler(s1);
        z3.addSchueler(s1);
        z3.addSchueler(s2);
        z1.addSchueler(s2);
        z1.setRaumNr(new Raum("abc", 20));
        z2.setRaumNr(new Raum("efg", 20));
        z3.setRaumNr(new Raum("hij", 20));

        List<Zuordnung> zuordnungen = new ArrayList<>();
        zuordnungen.add(z1);
        zuordnungen.add(z2);
        zuordnungen.add(z3);

        //When
        List<String> ergebnis = AusgabeSchueler.erstelleListeFuerAusgabe(schuelerListe, zuordnungen);
        assertTrue(ergebnis.size() == 12);

    }
}
