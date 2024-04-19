package datenEinlesen;

import datenmodelle.Timeslot_Enum;
import datenmodelle.Veranstaltung;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julia & Jo
 */
public class VorhandeneVeranstaltungen {

    private static final String PFAD_EXCEL = "./Eingabe/Unternehmen.xlsx";

    private static final String PFAD_CSV = "./Eingabe/Unternehmen.csv";

    /**
     * Diese Methode liest aus der angegebenen Datei (pfadExcel) die enthaltenen Unternehmen aus
     * und speichert die Zeilen in einer String-Liste
     *
     * @return Eine Liste von Unternehmens-Objekten
     * @throws IOException
     * @author Julia
     */
    private static List<String> auslesenVeranstaltungen() throws IOException {
        DateiKonvertieren.excelToCSV(PFAD_EXCEL, PFAD_CSV);
        Path file = Paths.get(PFAD_CSV);
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);
        return data;
    }

    /**
     * Diese Methode erstellt für die Zeilen in der Excel-Datei eine entsprechenden Unternehmens-Objekte
     * und speichert diese in einer Liste, die für die weitere Verarbeitung zurückgegeben wird
     *
     * @return Liste an Unternehmensobjekten
     * @throws IOException
     * @author Jo & Julia
     */
    public static List<Veranstaltung> erstellenVeranstaltungen() throws IOException {
        List<String> data = auslesenVeranstaltungen();

        Files.deleteIfExists(Paths.get(PFAD_CSV));

        return veranstaltungenGenerieren(data);
    }

    /**
     * Ausgelagerte Methode fuer Testzwecke
     *
     * @param data; Daten aus den die Veranstaltungen generiert werden
     * @return Liste der Veranstaltungen
     * EingabeParameter kann durch testdaten ersetzt werden ohne die Verwendung eines Mocking-Frameworks
     * Aufruf von außen bleibt unveraendert
     * @author Jo
     */
    protected static List<Veranstaltung> veranstaltungenGenerieren(List<String> data) {
        List<Veranstaltung> unternehmen = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            Veranstaltung unternehmen_temp = new Veranstaltung();
            String[] data_temp;
            data_temp = data.get(i).split(";");
            if (!data_temp[0].isBlank()) {
                unternehmen_temp.setId(Integer.parseInt(data_temp[0]));
                unternehmen_temp.setUnternehmen(data_temp[1]);
                unternehmen_temp.setFachrichtung(data_temp[2]);
                if (data_temp.length > 3 && !data_temp[3].isBlank()) {
                    unternehmen_temp.setMaxSchueler(Integer.parseInt(data_temp[3]));
                } else {
                    unternehmen_temp.setMaxSchueler(20);
                }
                if (data_temp.length > 4 && !data_temp[4].isBlank()) {
                    unternehmen_temp.setMaxVeranstaltungen(Integer.parseInt(data_temp[4]));
                } else {
                    unternehmen_temp.setMaxVeranstaltungen(5);
                }
                if (data_temp.length > 5 && !data_temp[5].isBlank()) {
                    unternehmen_temp.setFruehesterBeginn(Timeslot_Enum.valueOf(data_temp[5]));
                } else {
                    unternehmen_temp.setFruehesterBeginn(Timeslot_Enum.A);
                }
                unternehmen.add(unternehmen_temp);
            }
        }
        return unternehmen;
    }
}
