package datenEinlesen;

import com.spire.xls.ExcelVersion;
import com.spire.xls.IgnoreErrorType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Jo Duras
 * Klasse zur Konvertierung zwischen Excel und CSV
 * mithilfe von Ice-Blue
 */
public class DateiKonvertieren {

    /**
     *
     * @param input ; Pfad der Excel-Datei, die konvertiert werden soll
     * @param output ; Pfad der auszugebenen CSV-Datei
     * @throws IOException
     */
    public static void excelToCSV(String input, String output) throws IOException{
        Workbook workbook = new Workbook();
        workbook.loadFromFile(input);
        workbook.calculateAllValue();
        //erstes Sheet der Excel-Datei
        Worksheet sheet = workbook.getWorksheets().get(0);

        sheet.saveToFile(output, ";", StandardCharsets.ISO_8859_1);
    }

    /**
     *
     * @param input ; Pfad der CSV-Datei, die kionvertiert werden soll
     * @param output ; Pfad der auszugebenen Excel-Datei
     * @throws IOException
     */
    public static void csvToExcel(String input, String output) throws IOException{
        Workbook workbook = new Workbook();
        workbook.loadFromFile(input, ";");
        Worksheet sheet = workbook.getWorksheets().get(0);

        //Zeilen- und Spaltenanzahl festlegen
        int zeilenZahl = numberofLines(input);
        char spaltenZahl = numberOfCollumns(input);
        StringBuilder sb = new StringBuilder();
        sb.append(spaltenZahl).append(zeilenZahl);

        //Fehler werden ignoriert wennn Zahlen as Text gesetzt werden
        sheet.getCellRange("A1:" + sb).setIgnoreErrorOptions(EnumSet.of(IgnoreErrorType.NumberAsText));

        sheet.getAllocatedRange().autoFitColumns();
        sheet.getAllocatedRange().autoFitRows();
        workbook.saveToFile(output, ExcelVersion.Version2013);

    }

    /**
     * Hilfsmethode für die Ermittlung der Anzahl an Zeilen in der CSV-Datei
     * @param path; Pfad der Datei deren Zeilenlaenge ermittelt werden soll
     * @return Anzahl Zeilen als int Wert
     * @throws IOException
     */
    private static int numberofLines(String path) throws IOException {
        Path file = Paths.get(path);
        return Files.readAllLines(file).size();
    }

    /**
     * Hilfsmethode für die Ermittlung der Anzahl an Spalten in der CSV-Datei
     * @param path; Pfad der Datei deren Spaltenlaenge ermittelt werden soll
     * @return Anzahl Spalten als char Wert; erste Spalte = A
     * @throws IOException
     */
    private static char numberOfCollumns(String path) throws IOException{
        Path file = Paths.get(path);
        List<String> data = Files.readAllLines(file);
        //Typecasting von int auf char; 65 == A
        return (char) (data.get(0).split(";").length + 64);
    }
}
