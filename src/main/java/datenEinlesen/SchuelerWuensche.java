package datenEinlesen;

import datenmodelle.Schueler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jo Duras
 *
 */
public class SchuelerWuensche {

    private static final String pfadExcel = "H:/BOVS/Eingabe/Schuelerwuensche.xlsx";
    private static final String pfadCSV = "eingabe/Schuelerwuensche.csv";


    /**
     * Auslesen der Schuelerwuensche Datei
     * Generieren von Schueler-Objekten nach den ANgaben in der Datei
     * Array der Schuelerwuensche in Schueler-Objekt wird ebenfalls gefuellt
     * Wenn keine/teilweise Angabe von Wuenschen wird in die Liste eine 0 eingetragen
     *
     * @return liste aller teilnehmenden Schueler mit den eingetragenen wuenschen
     * @throws IOException
     */
    public static List<Schueler> auslesen() throws IOException {
        //Datei auslesen
        DateiKonvertieren.excelToCSV(pfadExcel, pfadCSV);
        Path file = Paths.get(pfadCSV);
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);

        return schuelerGenerieren(data);

    }

    protected static List<Schueler> schuelerGenerieren(List<String> data){
        List<Schueler> schuelerListe = new ArrayList<>();
        boolean titelzeile = true;
        for(String line : data) {
            //erste Zeile ueberspringen, da Titelzeile im Dokument
            if (titelzeile) {
                titelzeile = false;
                continue;
            }
            Schueler s = new Schueler();
            String[] info = line.split(";");
            if (!info[0].isBlank()) {
                s.setKlasse(info[0]);
                s.setNachname(info[1]);
                s.setVorname(info[2]);

                int[] wuensche = new int[6];
                for (int i = 0; i < 6; i++) {
                    //Wenn kein Wunsch angegeben ist wird eine 0 in das Array eingetragen
                    try {
                        if (info[3 + i].isBlank()) {
                            wuensche[i] = 0;
                        } else {
                            wuensche[i] = Integer.parseInt(info[3 + i]);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        //Wenn kein Wunsch angegeben ist wird eine 0 in das Array eingetragen
                        wuensche[i] = 0;
                    }
                }
                s.setWahl(wuensche);

                schuelerListe.add(s);
            }
        }

        return schuelerListe;

    }


}
