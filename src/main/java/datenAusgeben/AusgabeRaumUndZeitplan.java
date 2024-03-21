package datenAusgeben;

import datenEinlesen.DateiKonvertieren;
import datenmodelle.Zuordnung;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Julia, Maurice
 */
public class AusgabeRaumUndZeitplan {

    private static final String AUSGABE_ZEITPLAN = "H:/BOVS/Ausgabe/plan";

    public static void zeitplanListeErstellen(List<Zuordnung> zuordnungList ) throws IOException {
        List<String> data = erstelleZeitUndRaumplanFuerAusgabe(zuordnungList);
        schreibeInDatei(data);
    }

    protected static List<String> erstelleZeitUndRaumplanFuerAusgabe(List<Zuordnung> zuordnungList) {
        List<String> data = new ArrayList<>();
        for(Zuordnung z : zuordnungList) {

        }
        return data;
    }




    private static void schreibeInDatei(List<String> data) throws IOException {
        Path csvFile = Paths.get(AUSGABE_ZEITPLAN + ".csv");
        Files.deleteIfExists(csvFile);
        Files.createFile(csvFile);
        for (String line : data) {
            line = line + "\n";
            Files.write(csvFile, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }
        DateiKonvertieren.csvToExcel(AUSGABE_ZEITPLAN + ".csv", AUSGABE_ZEITPLAN + ".xlsx");
        Files.deleteIfExists(csvFile);

    }


}
