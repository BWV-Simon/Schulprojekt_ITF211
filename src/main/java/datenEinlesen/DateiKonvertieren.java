package datenEinlesen;

import com.spire.xls.ExcelVersion;
import com.spire.xls.IgnoreErrorType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;

/**
 * @author Jo Duras
 *
 * Klasse zur Konvertierung zwischen Excel und CSV
 * mithilfe von Ice-Blue
 */
public class DateiKonvertieren {

    private static Workbook workbook = new Workbook();

    /**
     *
     * @param input ; Pfad der Excel-Datei, die kionvertiert werden soll
     * @param output ; Pfad der auszugebenen CSV-Datei
     */
    public static void excelToCSV(String input, String output){
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
     */
    public static void CSVToExcel(String input, String output){
        workbook.loadFromFile(input);
        Worksheet sheet = workbook.getWorksheets().get(0);

        sheet.getAllocatedRange().autoFitColumns();
        sheet.getAllocatedRange().autoFitRows();
        workbook.saveToFile(output, ExcelVersion.Version2013);

    }
}
