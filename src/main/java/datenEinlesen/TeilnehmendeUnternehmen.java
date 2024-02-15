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

    public static List<Unternehmen> auslesen() throws IOException {
        DateiKonvertieren.excelToCSV(pfadExcel, pfadCSV);
        Path file = Paths.get(pfadCSV);
        List<Unternehmen> unternehmen = new ArrayList<>();
        String[] dataobject;
        String id_temp;
        String name_temp;
        String fachrichtung_temp;
        String kategorie_temp;
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);

        for (int i = 1; i < data.size(); i++) {
            Unternehmen unternehmen_temp = new Unternehmen();
            dataobject = data.get(i).split(";");
            id_temp = dataobject[0];
            name_temp = dataobject[1];
            fachrichtung_temp = dataobject[2];
            kategorie_temp = dataobject[3];
            unternehmen_temp.setId(Integer.parseInt(id_temp));
            unternehmen_temp.setName(name_temp);
            unternehmen_temp.setFachrichtung(fachrichtung_temp);
            unternehmen_temp.setKategorie(ermittleKategorie(kategorie_temp));
            System.out.println(unternehmen_temp.getName());
            unternehmen.add(unternehmen_temp);
        }
        return unternehmen;
    }

    private static Kategorie ermittleKategorie(String kategorie) {
        switch (kategorie) {
            case "kaufmännisch":
                return Kategorie.KAUFMAENNISCH;
            case "Verwaltung":
                return Kategorie.VERWALTUNG;
            case "Studium":
                return Kategorie.STUDIUM;
            case "Studierende":
                return Kategorie.STUDIERENDE;
            default:
                throw new IllegalArgumentException("Diese Kategorie gibt es nicht.");
        }
    }


}
