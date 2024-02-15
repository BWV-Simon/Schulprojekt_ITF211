package datenEinlesen;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Julia Hemkendreis
 */
public class TeilnehmendeUnternehmen {

    private static final String pfadExcel = "eingabe/Unternehmen.xlsx";

    private static final String pfadCSV = "eingabe/Unternehmen.csv";


    public static void auslesen() throws IOException {
        DateiKonvertieren.excelToCSV(pfadExcel, pfadCSV);
        Path file = Paths.get(pfadCSV);
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);
        for(String line : data){
            System.out.println(line);
        }

    }

}