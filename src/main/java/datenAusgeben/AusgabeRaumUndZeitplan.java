package datenAusgeben;

import datenEinlesen.DateiKonvertieren;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * @author Julia, Maurice
 */
public class AusgabeRaumUndZeitplan {

    private static final String AUSGABE_ZEITPLAN = "H:/BOVS/Ausgabe/plan";

    public static void zeitplanListeErstellen(HashMap<Veranstaltung, List<Zuordnung>> veranstaltungMap, double score) throws IOException {
        List<String> data = erstelleZeitUndRaumplanFuerAusgabe(veranstaltungMap, score);
        schreibeInDatei(data);
    }

    protected static List<String> erstelleZeitUndRaumplanFuerAusgabe(HashMap<Veranstaltung, List<Zuordnung>> zuordnungen, double score) {
        List<String> data = new ArrayList<>();
        data.add("Organisationsplan");
        data.add("Begrüßung:" + ";" + "08:30-08:45");
        data.add("Abschluss:" + ";" + "13:10-13:20");
        data.add("Erfüllungsscore:" + ";" + String.valueOf(score) + "%");
        data.add("");
        data.add(""
                + ";" + "08:45-09:30"
                + ";" + "09:50-10:35"
                + ";" + "10:35-11:20"
                + ";" + "11:40-12:25"
                + ";" + "12:25-13:10");
        for (Veranstaltung v : zuordnungen.keySet()) {
            String temp = v.getUnternehmen();
            temp += ";";
            List<Zuordnung> zuordnungList = zuordnungen.get(v);
            Comparator<Zuordnung> comparator = Comparator.comparing(Zuordnung::getZeitpunkt);
            Collections.sort(zuordnungList, comparator);
            for (Zuordnung z : zuordnungList) {
                if (z.getSchuelerList().size() > 0) {
                    temp += z.getRaumNr().getRaumname();
                    temp += ";";
                } else {
                    temp += ";";
                }
            }
            data.add(temp);
        }
        return data;
    }


    private static void schreibeInDatei(List<String> data) throws IOException {
        Path csvFile = Paths.get(AUSGABE_ZEITPLAN + ".csv");
        Files.deleteIfExists(csvFile);
        Files.createFile(csvFile);
        for (String line : data) {
            line = line + "\n";
            Files.write(csvFile, line.getBytes(StandardCharsets.ISO_8859_1), StandardOpenOption.APPEND);
        }
        DateiKonvertieren.csvToExcel(AUSGABE_ZEITPLAN + ".csv", AUSGABE_ZEITPLAN + ".xlsx");
        Files.deleteIfExists(csvFile);

    }


}
