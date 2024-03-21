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
 * @author Julia
 */
public class VorhandeneVeranstaltungen {

    private static final String PFAD_EXCEL = "H:/BOVS/Eingabe/Unternehmen.xlsx";

    private static final String PFAD_CSV = "eingabe/Unternehmen.csv";

    /** Diese Methode liest aus der angegebenen Datei (pfadExcel) die enthaltenen Unternehmen aus
     *  und speichert die Zeilen in einer String-Liste
     * @return Eine Liste von Unternehmensobjekten
     * @throws IOException
     */
    private static List<String> auslesenVeranstaltungen() throws IOException {
        DateiKonvertieren.excelToCSV(PFAD_EXCEL, PFAD_CSV);
        Path file = Paths.get(PFAD_CSV);
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);
        return data;
    }

    /** Diese Methode erstellt für die Zeilen in der Excel-Datei eine entsprechenden Unternehmensobjekte
     * und speichert diese in einer Liste, die für die weitere Verarbeitung zurückgegeben wird.
     * @return Liste an Unternehmensobjekten
     */
    public static List<Veranstaltung> erstellenVeranstaltungen() throws IOException {
        List<String> data = auslesenVeranstaltungen();
        return veranstaltungenGenerieren(data);
    }

    /**
     * Ausgelagerte Methode fuer Testzwecke
     * EingabeParameter kann durch testdaten ersetzt werden ohne die Verwendung eines Mocking-Frameworks
     * Aufruf von außen bleibt unveraendert
     *
     * @author Jo Duras
     * @param data; Daten aus den die Veranstaltungen generiert werden
     * @return Liste der Veranstaltungen
     */
    protected static List<Veranstaltung> veranstaltungenGenerieren(List<String> data) {
        List<Veranstaltung> unternehmen = new ArrayList<>();
        String[] data_temp;
        for (int i = 1; i < data.size(); i++) {
            Veranstaltung unternehmen_temp = new Veranstaltung();
            data_temp = data.get(i).split(";");
            if(!data_temp[0].isBlank()) {
                unternehmen_temp.setId(Integer.parseInt(data_temp[0]));
                unternehmen_temp.setUnternehmen(data_temp[1]);
                unternehmen_temp.setFachrichtung(data_temp[2]);
                if (data_temp.length > 3  && !data_temp[3].isBlank()) {
                    unternehmen_temp.setMaxSchueler(Integer.parseInt(data_temp[3]));
                }
                if (data_temp.length > 4 && !data_temp[4].isBlank()) {
                    unternehmen_temp.setMaxVeranstaltungen(Integer.parseInt(data_temp[4]));
                }
                if (data_temp.length > 5 && !data_temp[5].isBlank()){
                    unternehmen_temp.setFruehesterBeginn(Timeslot_Enum.valueOf(data_temp[5]));
                }
                unternehmen.add(unternehmen_temp);
            }
        }
        return unternehmen;
    }


}
