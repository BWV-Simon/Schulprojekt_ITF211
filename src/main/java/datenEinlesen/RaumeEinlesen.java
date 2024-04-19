package datenEinlesen;

import datenmodelle.Raum;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julia & Maurice
 */
public class RaumeEinlesen {

    private static final String PFAD_EXCEL = "./Eingabe/Raeume.xlsx";

    private static final String PFAD_CSV = "./Eingabe/Raeume.csv";

    private static List<String> auslesenRaeume() throws IOException {
        DateiKonvertieren.excelToCSV(PFAD_EXCEL, PFAD_CSV);
        Path file = Paths.get(PFAD_CSV);
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);
        return data;
    }

    public static List<Raum> erstellenRaume() throws IOException {
        List<String> data = auslesenRaeume();
        return raeumeErstellen(data);
    }

    /**
     * Raeume werden erstellt und in ArrayList gespeichert und zurueckgegeben
     * @author Julia & Maurice
     * @param data
     * @return raeume
     */
    protected static List<Raum> raeumeErstellen(List<String> data) {
        List<Raum> raeume = new ArrayList<>();
        String[] data_temp;
        for(int i = 1; i < data.size(); i++) {
            Raum raum_temp = new Raum();
            data_temp = data.get(i).split(";");
            if(!data_temp[0].isBlank()) {
                raum_temp.setRaumname(data_temp[0]);
                raum_temp.setKapzität(Integer.parseInt(data_temp[1]));
            }
            raeume.add(raum_temp);
        }
        return raeume;
    }
}
