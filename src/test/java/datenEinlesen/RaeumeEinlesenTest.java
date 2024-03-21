package datenEinlesen;
import static org.junit.Assert.assertTrue;

import datenmodelle.Raum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julia
 */
public class RaeumeEinlesenTest {


    @Test
    void raeumeErstellenTest() {
        List<String> mockData = new ArrayList<>();
        mockData.add("Raum;Kapazität");
        mockData.add("Aula;50");
        List<Raum> raume = RaumeEinlesen.raeumeErstellen(mockData);
        assertTrue(raume.size() == 1);
    }
}
