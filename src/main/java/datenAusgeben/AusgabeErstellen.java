package datenAusgeben;

import datenEinlesen.DateiKonvertieren;
import datenmodelle.Schueler;
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
public class AusgabeErstellen {

    private static final String AUSGABEZUORDNUNG = "ausgabe/zuordnung";

    /**
     * Erstellt Anwesenheitslisten fuer die Unternehmen, damit diese ueberpruefen koennne, ob alls Schule anwesend sind
     *
     * @param zuordnungen
     * @throws IOException
     */
    public static void zuordnungErstellen(List<Zuordnung> zuordnungen) throws IOException {
        List<String> data = new ArrayList<>();

        for (Zuordnung z : zuordnungen) {
            data.add(z.getVeranstaltung().getUnternehmen() + ";" + z.getVeranstaltung().getFachrichtung());
            data.add(z.getZeitpunkt().toString());
            data.add("");

            data.add("Klasse;Nachname;Vorname;Anwesend?");
            for (Schueler s : z.getSchuelerList()) {
                data.add(s.toCSVString());
            }
            data.add("");
        }
        Path csvFile = Paths.get(AUSGABEZUORDNUNG + ".csv");
        Files.deleteIfExists(csvFile);
        Files.createFile(csvFile);

        for (String line : data) {
            line = line + "\n";
            Files.write(csvFile, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }

        DateiKonvertieren.csvToExcel(AUSGABEZUORDNUNG + ".csv", AUSGABEZUORDNUNG + ".xlsx");

    }
}
