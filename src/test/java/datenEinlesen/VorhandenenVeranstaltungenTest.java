package datenEinlesen;
import static org.junit.Assert.assertTrue;

import datenmodelle.Timeslot_Enum;
import datenmodelle.Veranstaltung;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jo Duras
 */
public class VorhandenenVeranstaltungenTest {

    /**
     * Wenn eine vollstaendige Veranstaltung in der Liste steht,
     * Dann wird diese Veranstat´ltung richtig generiert
     */
    @Test
    void vollstaendigeVeranstaltungWirdErstellt(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Nr. ;Unternehmen;Fachrichtung;Max. Teilnehmer;Max. Veranstaltungen;Fr�hester Zeitpunkt;");
        mockData.add("1;Testfirma;Testen;28;4;B;");

        List<Veranstaltung> testDaten =   VorhandeneVeranstaltungen.veranstaltungenGenerieren(mockData);
        Veranstaltung testVeranstaltung = testDaten.get(0);


        assertTrue(testVeranstaltung.getId() == 1);
        assertTrue(testVeranstaltung.getUnternehmen().equalsIgnoreCase("testfirma"));
        assertTrue(testVeranstaltung.getFachrichtung().equalsIgnoreCase("testen"));
        assertTrue(testVeranstaltung.getMaxSchueler() == 28);
        assertTrue(testVeranstaltung.getMaxVeranstaltungen() == 4);
        assertTrue(testVeranstaltung.getFruehesterBeginn() == Timeslot_Enum.B);

    }

    /**
     * Wenn keine Maximal SchuelerAnzahl in der Datei angegeben ist,
     * Dann wird die Maximale Anzahl auf 20 gesetzt
     */
    @Test
    void standardwertFuerMaxAnzahlSchueler(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Nr. ;Unternehmen;Fachrichtung;Max. Teilnehmer;Max. Veranstaltungen;Fr�hester Zeitpunkt;");
        mockData.add("1;Testfirma;Testen;;5;A;");

        List<Veranstaltung> testDaten =   VorhandeneVeranstaltungen.veranstaltungenGenerieren(mockData);
        Veranstaltung testVeranstaltung = testDaten.get(0);

        assertTrue(testVeranstaltung.getMaxSchueler() == 20);
    }

    /**
     * Wenn keine Maximal Anzahl an Veranstaltungen in der Datei angegeben ist,
     * Dann wird die Maximale Zahl auf 5 gesetzt
     */
    @Test
    void standardwertFuerMaxVeranstaltungen(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Nr. ;Unternehmen;Fachrichtung;Max. Teilnehmer;Max. Veranstaltungen;Fr�hester Zeitpunkt;");
        mockData.add("1;Testfirma;Testen;30;;A;");

        List<Veranstaltung> testDaten =   VorhandeneVeranstaltungen.veranstaltungenGenerieren(mockData);
        Veranstaltung testVeranstaltung = testDaten.get(0);

        assertTrue(testVeranstaltung.getMaxVeranstaltungen() == 5);

    }

    /**
     * Wenn in nder Datei kein fruehster Beginn angegeben ist,
     * Dann wird der fruehstmoeglichste Termin(Timeslot A) als Standardwert genommen
     */
    @Test
    void standardwertFuerFruehsterBeginn(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Nr. ;Unternehmen;Fachrichtung;Max. Teilnehmer;Max. Veranstaltungen;Fr�hester Zeitpunkt;");
        mockData.add("1;Testfirma;Testen;30;3;;");

        List<Veranstaltung> testDaten =   VorhandeneVeranstaltungen.veranstaltungenGenerieren(mockData);
        Veranstaltung testVeranstaltung = testDaten.get(0);

        assertTrue(testVeranstaltung.getFruehesterBeginn() == Timeslot_Enum.A);

    }

    /**
     * Wenn in der Datei eine leere Zeile steht,
     * Dann wird dafür kein Veranstaltungs-Objekt erzeugt
     */
    @Test
    void leereZeilenWerdenNichtAlsVeranstaltungErkannt(){
        List<String> mockData = new ArrayList<>();
        mockData.add("Nr. ;Unternehmen;Fachrichtung;Max. Teilnehmer;Max. Veranstaltungen;Fr�hester Zeitpunkt;");
        mockData.add("");
        mockData.add("1;Testfirma;Testen;10;1;C;");
        mockData.add("");
        mockData.add("1;Testfirma2;Testen;26;4;A;");

        List<Veranstaltung> testDaten =   VorhandeneVeranstaltungen.veranstaltungenGenerieren(mockData);

        assertTrue(testDaten.size() == 2);
    }
}
