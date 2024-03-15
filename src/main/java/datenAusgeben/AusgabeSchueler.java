package datenAusgeben;

import datenEinlesen.DateiKonvertieren;
import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jo Duras
 */
public class AusgabeSchueler {

    private static final String AUSGABESCHUELER = "";

    public static void SchuelerListenErstellen(List<Schueler> schuelerListe, List<Zuordnung> zuordnungen) throws IOException {
        List<String> data = erstelleListeFuerAusgabe(schuelerListe, zuordnungen);
        schreibeInDatei(data);
    }

    protected static List<String> erstelleListeFuerAusgabe(List<Schueler> schuelerListe, List<Zuordnung> zuordnungen) throws IOException {
        List<String> data = new ArrayList<>();
        int i = 1;

        for (Schueler s : schuelerListe) {
            data.add(s.getKlasse());
            data.add(s.getNachname() + ", " + s.getVorname());
            data.add(";Zeit;Raum;Veranstaltung;;");


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

    protected static List<Zuordnung> getVeranstaltungFuerSchueler(Schueler s, List<Zuordnung> zuordnungen) {
        List<Zuordnung> temp = new ArrayList<>();

        for (Zuordnung z : zuordnungen) {
            for (Schueler teilnehmer : z.getSchuelerList()) {
                if (s.equals(teilnehmer)) {
                    temp.add(z);
                }
            }
        }
        return temp;
    }

    private static void schreibeInDatei(List<String> data) throws IOException {
        Path csvFile = Paths.get(AUSGABESCHUELER + ".csv");
        Files.deleteIfExists(csvFile);
        Files.createFile(csvFile);

        for (String line : data) {
            line = line + "\n";
            Files.write(csvFile, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }

        DateiKonvertieren.csvToExcel(AUSGABESCHUELER + ".csv", AUSGABESCHUELER + ".xlsx");

    }
}
