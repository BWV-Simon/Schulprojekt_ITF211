package datenEinlesen;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Jo Duras
 *
 */
public class SchuelerWuensche {

    private static final String pfad = "eingabe/Schuelerwuensche.xlsx";

    public static void auslesen() throws IOException {
        Path file = Paths.get(pfad);
        List<String> data = Files.readAllLines(file, StandardCharsets.ISO_8859_1);


        for(String line : data){
            System.out.println(line);
        }


    }


}
