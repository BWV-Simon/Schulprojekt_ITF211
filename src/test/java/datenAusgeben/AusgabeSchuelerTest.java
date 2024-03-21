package datenAusgeben;

import datenmodelle.Schueler;
import datenmodelle.Timeslot_Enum;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;
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
        Veranstaltung v1 = new Veranstaltung(1, "Musterveranstaltung", "IT");
        Veranstaltung v2 = new Veranstaltung(2, "Musteruni", "Wirtschaft");
        Zuordnung z1 = new Zuordnung(Timeslot_Enum.A, v1);
        Zuordnung z2 = new Zuordnung(Timeslot_Enum.B, v1);
        Zuordnung z3 = new Zuordnung(Timeslot_Enum.C, v2);

        z2.addSchueler(s1);
        z3.addSchueler(s1);
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

        Veranstaltung v1 = new Veranstaltung(1, "Musterveranstaltung", "IT");
        Veranstaltung v2 = new Veranstaltung(2, "Musteruni", "Wirtschaft");
        Zuordnung z1 = new Zuordnung(Timeslot_Enum.A, v1);
        Zuordnung z2 = new Zuordnung(Timeslot_Enum.B, v1);
        Zuordnung z3 = new Zuordnung(Timeslot_Enum.C, v2);
        z2.addSchueler(s1);
        z3.addSchueler(s1);
        z3.addSchueler(s2);
        z1.addSchueler(s2);

        List<Zuordnung> zuordnungen = new ArrayList<>();
        zuordnungen.add(z1);
        zuordnungen.add(z2);
        zuordnungen.add(z3);

        //When
        List<String> ergebnis = AusgabeSchueler.erstelleListeFuerAusgabe(schuelerListe, zuordnungen);

        //Then
        for (String line : ergebnis) {
            System.out.println(line);
        }

        assertTrue(ergebnis.size() == 12);

    }
}
