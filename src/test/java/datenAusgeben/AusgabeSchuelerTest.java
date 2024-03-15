package datenAusgeben;

import datenmodelle.Schueler;
import datenmodelle.Timeslot_Enum;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;
import org.junit.Test;

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
        Schueler s = new Schueler();

        Zuordnung z1 = new Zuordnung();
        Zuordnung z2 = new Zuordnung();
        Zuordnung z3 = new Zuordnung();

        z2.addSchueler(s);
        z3.addSchueler(s);
        List<Zuordnung> zuordnungen = new ArrayList<>();
        zuordnungen.add(z1);
        zuordnungen.add(z2);
        zuordnungen.add(z3);

        //When
        List<Zuordnung> testErgebnis = AusgabeSchueler.getVeranstaltungFuerSchueler(s, zuordnungen);

        //Then
        assertTrue(testErgebnis.size() == 2);

    }

    @Test
    public void erstelleListeFuerAusgabeTest() {
        //Given
        Schueler s1 = new Schueler();
        Schueler s2 = new Schueler();

        List<Schueler> schuelerListe = new ArrayList<>();
        schuelerListe.add(s1);
        schuelerListe.add(s2);

        Zuordnung z1 = new Zuordnung(Timeslot_Enum.A, new Veranstaltung());
        Zuordnung z2 = new Zuordnung(Timeslot_Enum.B, new Veranstaltung());
        Zuordnung z3 = new Zuordnung(Timeslot_Enum.C, new Veranstaltung());

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
