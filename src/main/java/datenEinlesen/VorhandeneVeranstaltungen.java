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
 * @author Julia Hemkendreis
 */
public class VorhandeneVeranstaltungen {

    private static final String pfadExcel = "eingabe/Unternehmen.xlsx";

    private static final String pfadCSV = "eingabe/Unternehmen.csv";

    /** Diese Methode liest aus der angegebenen Datei (pfadExcel) die enthaltenen Unternehmen aus
     *  und speichert die Zeilen in einer String-Liste
     * @return Eine Liste von Unternehmensobjekten
     * @throws IOException
     */
    private static List<String> auslesenVeranstaltungen() throws IOException {
        DateiKonvertieren.excelToCSV(pfadExcel, pfadCSV);
        Path file = Paths.get(pfadCSV);
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);
        return data;
    }

    /** Diese Methode erstellt für die Zeilen in der Excel-Datei eine entsprechenden Unternehmensobjekte
     * und speichert diese in einer Liste, die für die weitere Verarbeitung zurückgegeben wird.
     * @return Liste an Unternehmensobjekten
     */
    public List<Veranstaltung> erstellenVeranstaltungen() throws IOException {
        List<String> data = auslesenVeranstaltungen();
        List<Veranstaltung> unternehmen = new ArrayList<>();
        String[] data_temp;
        for (int i = 1; i < data.size(); i++) {
            Veranstaltung unternehmen_temp = new Veranstaltung();
            data_temp = data.get(i).split(";");
            if(!data_temp[0].isBlank()) {
                unternehmen_temp.setId(Integer.parseInt(data_temp[0]));
                unternehmen_temp.setUnternehmen(data_temp[1]);
                unternehmen_temp.setFachrichtung(data_temp[2]);
                if (!data_temp[3].isBlank()) {
                    unternehmen_temp.setMaxSchueler(Integer.parseInt(data_temp[3]));
                }
                if (!data_temp[4].isBlank()) {
                    unternehmen_temp.setMaxVeranstaltungen(Integer.parseInt(data_temp[4]));
                }
                unternehmen_temp.setFruehesterBeginn(Timeslot_Enum.valueOf(data_temp[5]));
                unternehmen.add(unternehmen_temp);
            }
        }
        return unternehmen;
    }


}
