package datenAusgeben;

import datenmodelle.Schueler;
import datenmodelle.Zuordnung;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class AusgabeSchuelerTest {

    @Test
    public void getVeranstaltungFuerSchuelerTest() {
        //Given
        Schueler s = new Schueler();
        s.setNachname("Test");

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
}
