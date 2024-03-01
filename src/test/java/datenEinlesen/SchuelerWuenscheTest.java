package datenEinlesen;
import static org.junit.Assert.assertTrue;

import datenmodelle.Schueler;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jo Duras
 */
public class SchuelerWuenscheTest {


    /**
     * Wenn ein vollstaendiger Schueler in den Testdaten vorhanden ist,
     * dann wird dieser mit allen Wuenschen richtig generiert
     */
    @Test
    void vollstaendigerSchuelerWirdVerarbeitet(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Klasse;Name;Vorname;Wahl 1;Wahl 2;Wahl 3;Wahl 4;Wahl 5;Wahl 6 (Erstazwunsch)");
        mockData.add("Testklasse;Test;Tester;1;2;3;4;5;6");

        List<Schueler> testdaten = SchuelerWuensche.schuelerGenerieren(mockData);
        Schueler testSchueler = testdaten.get(0);

        //Ueberpruefen der Klasse
        assertTrue(testSchueler.getKlasse().equalsIgnoreCase("testklasse") );

        //Ueberpruefen der einzelnen Wuensche
        for(int i = 0; i < 6; i++){
            int testWunsch = testSchueler.getWahl()[i];
            assertTrue(testWunsch == (i+1));
        }
    }

    /**
     * Wenn ein Schueler keine Wuensche angibt,+
     * Dann werden diese Wuensche auf den int-Wert 0 gesetzt
     */
    @Test
    void schuelerOhneWunsch(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Klasse;Name;Vorname;Wahl 1;Wahl 2;Wahl 3;Wahl 4;Wahl 5;Wahl 6 (Erstazwunsch)");
        mockData.add("Testklasse;Test;Tester;;;;;;");

        List<Schueler> testdaten = SchuelerWuensche.schuelerGenerieren(mockData);
        Schueler testSchueler = testdaten.get(0);

        //Ueberpruefen der einzelnen Wuensche
        for(int i = 0; i < 6; i++){
            int testWunsch = testSchueler.getWahl()[i];
            assertTrue(testWunsch == 0);
        }
    }

    /**
     * Wenn ein Schueler nur einen Teil der Wuensche angibt,
     * Dann werden die angegebenen Wuensche beruecktsichtigt
     * Und die anderen Wuensche werden auf den int-Wert 0 gesetzt
     */
    @Test
    void schuelerMitTeilwuenschen(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Klasse;Name;Vorname;Wahl 1;Wahl 2;Wahl 3;Wahl 4;Wahl 5;Wahl 6 (Erstazwunsch)");
        mockData.add("Testklasse;Test;Tester;1;2;;;;");

        List<Schueler> testdaten = SchuelerWuensche.schuelerGenerieren(mockData);
        Schueler testSchueler = testdaten.get(0);

        int testWunsch1 = testSchueler.getWahl()[0];
        int testWunsch2 = testSchueler.getWahl()[1];
        int testWunsch3 = testSchueler.getWahl()[2];
        int testWunsch4 = testSchueler.getWahl()[3];
        int testWunsch5 = testSchueler.getWahl()[4];
        int testWunsch6 = testSchueler.getWahl()[5];

        assertTrue(testWunsch1 == 1);
        assertTrue(testWunsch2 == 2);
        assertTrue(testWunsch3 == 0);
        assertTrue(testWunsch4 == 0);
        assertTrue(testWunsch5 == 0);
        assertTrue(testWunsch6 == 0);

    }

    /**
     * Wenn in den Daten einen leere Zeile vorhanden ist,
     * Dann wird diese nicht als Schueler erkannt
     */
    @Test
    void leereZeilenWerdenNichtAlsSchuelerErkannt(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Klasse;Name;Vorname;Wahl 1;Wahl 2;Wahl 3;Wahl 4;Wahl 5;Wahl 6 (Erstazwunsch)");
        mockData.add("");
        mockData.add("Testklasse;Test;Tester;1;2;;;;");

        List<Schueler> testdaten = SchuelerWuensche.schuelerGenerieren(mockData);

        assertTrue(testdaten.size() == 1);

    }
}
