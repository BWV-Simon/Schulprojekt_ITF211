package datenAusgeben;

import datenEinlesen.DateiKonvertieren;
import datenmodelle.Schueler;
import datenmodelle.Timeslot_Enum;
import datenmodelle.Zuordnung;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jo
 */
public class AusgabeSchueler {

    private static final String AUSGABESCHUELER = "./Ausgabe/schuelerlisten";

    /**
     * Erstellt die Excel-Listen fuer alle Schueler mit den Veranstaltungen
     *
     * @param schuelerListe
     * @param zuordnungen
     * @throws IOException
     * @author Julia & Jo
     */
    public static void schuelerListenErstellen(List<Schueler> schuelerListe, List<Zuordnung> zuordnungen) throws IOException {
        List<String> data = erstelleListeFuerAusgabe(schuelerListe, zuordnungen);
        schreibeInDatei(data);
    }

    /**
     * erstellt die Liste fuer die Ausgabe
     * @author Julia & Jo
     * @param schuelerListe
     * @param zuordnungen
     * @return LIst<String> fuer Ausgabe
     */
    protected static List<String> erstelleListeFuerAusgabe(List<Schueler> schuelerListe, List<Zuordnung> zuordnungen) {
        List<String> data = new ArrayList<>();
        List<Timeslot_Enum> zeiten = new ArrayList<>();
        boolean istGesetzt;
        zeiten.add(Timeslot_Enum.A);
        zeiten.add(Timeslot_Enum.B);
        zeiten.add(Timeslot_Enum.C);
        zeiten.add(Timeslot_Enum.D);
        zeiten.add(Timeslot_Enum.E);
        int i = 1;
        for (Schueler s : schuelerListe) {
            data.add(s.getKlasse());
            data.add(s.getNachname() + ", " + s.getVorname());
            data.add(";Zeit;Raum;Veranstaltung;;");
            List<Zuordnung> zuordnungSchueler = getVeranstaltungFuerSchueler(s, zuordnungen);
            for(Timeslot_Enum t : zeiten) {
                istGesetzt = false;
                for(Zuordnung z : zuordnungSchueler) {
                    if(z.getZeitpunkt() == t) {
                        data.add(z.getZeitpunkt().name() + ";" + z.getZeitpunkt().toString()
                                + ";" + z.getRaumNr().getRaumname()
                                + ";" + z.getVeranstaltung().getUnternehmen()
                                + ";" + z.getVeranstaltung().getFachrichtung());
                        istGesetzt = true;
                    }
                }
                if (!istGesetzt) {
                    data.add(t.name() + ";" + t);
                }
            }
            if (i == 4) {
                data.add("");
                data.add("");
                data.add("");
                i = 1;
            } else {
                i++;
                data.add("");
            }
        }

        return data;
    }

    /**
     * Isoliert die Veranstaltungen denen ein Schueler zugeordnet ist
     * @author Julia & Jo
     * @param s
     * @param zuordnungen
     * @return List<Zuordnung> Zuordnungen fuer einen spezifischen Schueler
     */
    protected static List<Zuordnung> getVeranstaltungFuerSchueler(Schueler s, List<Zuordnung> zuordnungen) {
        List<Zuordnung> temp = new ArrayList<>();

        for (Zuordnung z : zuordnungen) {
            for (Schueler teilnehmer : z.getSchuelerList()) {
                if (s.equals(teilnehmer)) {
                    temp.add(z);
                }
            }
        }
        Comparator<Zuordnung> comparator = Comparator.comparing(Zuordnung::getZeitpunkt);
        Collections.sort(temp, comparator);
        return temp;
    }

    /**
     * schreibt die Daten in die Datei
     * @author Julia & Jo
     * @param data
     * @throws IOException
     */
    private static void schreibeInDatei(List<String> data) throws IOException {
        Path csvFile = Paths.get(AUSGABESCHUELER + ".csv");
        Files.deleteIfExists(csvFile);
        Files.createFile(csvFile);

        for (String line : data) {
            line = line + "\n";
            Files.write(csvFile, line.getBytes(StandardCharsets.ISO_8859_1), StandardOpenOption.APPEND);
        }

        DateiKonvertieren.csvToExcel(AUSGABESCHUELER + ".csv", AUSGABESCHUELER + ".xlsx");
        Files.deleteIfExists(csvFile);

    }
}
