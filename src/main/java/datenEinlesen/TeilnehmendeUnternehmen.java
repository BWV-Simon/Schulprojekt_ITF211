package datenEinlesen;

import datenmodelle.Kategorie;
import datenmodelle.Unternehmen;

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
public class TeilnehmendeUnternehmen {

    private static final String pfadExcel = "eingabe/Unternehmen.xlsx";

    private static final String pfadCSV = "eingabe/Unternehmen.csv";

    /** Diese Methode liest aus der angegebenen Datei (pfadExcel) die enthaltenen Unternehmen aus
     *  und speichert die Zeilen in einer String-Liste
     * @return Eine Liste von Unternehmensobjekten
     * @throws IOException
     */
    public static List<String> auslesenUnternehm() throws IOException {
        DateiKonvertieren.excelToCSV(pfadExcel, pfadCSV);
        Path file = Paths.get(pfadCSV);
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);
        return data;
    }

    /** Diese Methode erstellt für die Zeilen in der Excel-Datei eine entsprechenden Unternehmensobjekte
     * und speichert diese in einer Liste, die für die weitere Verarbeitung zurückgegeben wird.
     * @TODO Anpassungen für den frühesten Beginn und maximale Schüleranzahl sobald Excel-Datei vorhanden ist.
     * @param data ; Die ausgelesene Liste aus der Excel-Datei
     * @return Liste an Unternehmensobjekten
     */
    public List<Unternehmen> erstellenUnternehmen(List<String> data) {
        List<Unternehmen> unternehmen = new ArrayList<>();
        String[] data_temp;
        for (int i = 1; i < data.size(); i++) {
            Unternehmen unternehmen_temp = new Unternehmen();
            data_temp = data.get(i).split(";");
            unternehmen_temp.setId(Integer.parseInt(data_temp[0]));
            unternehmen_temp.setName(data_temp[1]);
            unternehmen_temp.setFachrichtung(data_temp[2]);
            unternehmen_temp.setKategorie(Kategorie.ermittleKategorie(data_temp[3]));
            unternehmen.add(unternehmen_temp);
        }
        return unternehmen;
    }


}
