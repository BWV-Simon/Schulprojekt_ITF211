package algorithmus;

import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Simon, Jo, Julia
 */
public class UtilsTest {

    /**
     * @author Simon, Jo, Julia
     * Unit-Test für die Methode counter in der Klasse Utils
     */
    @Test
    public void counterTest(){
        // given
        Veranstaltung veranstaltung1 = new Veranstaltung();
        veranstaltung1.setId(1);
        veranstaltung1.setMaxSchueler(20);
        veranstaltung1.setMaxVeranstaltungen(5);

        Veranstaltung veranstaltung2 = new Veranstaltung();
        veranstaltung2.setId(2);
        veranstaltung2.setMaxSchueler(20);
        veranstaltung2.setMaxVeranstaltungen(5);

        Veranstaltung veranstaltung3 = new Veranstaltung();
        veranstaltung3.setId(3);
        veranstaltung3.setMaxSchueler(20);
        veranstaltung3.setMaxVeranstaltungen(5);

        ArrayList<Veranstaltung> veranstaltungsListe = new ArrayList<>();
        veranstaltungsListe.add(veranstaltung1);
        veranstaltungsListe.add(veranstaltung2);
        veranstaltungsListe.add(veranstaltung3);

        Schueler schueler1 = new Schueler();
        schueler1.setWahl(new int[]{1, 2, 3, 4, 5, 6});

        Schueler schueler2 = new Schueler();
        schueler2.setWahl(new int[]{1, 2, 3, 4, 5, 6});

        Schueler schueler3 = new Schueler();
        schueler3.setWahl(new int[]{1, 2, 3, 4, 5, 6});

        ArrayList<Schueler> schuelerListe = new ArrayList<>();
        schuelerListe.add(schueler1);
        schuelerListe.add(schueler2);
        schuelerListe.add(schueler3);

        //when
        HashMap<Veranstaltung, List<Schueler>> resultMap= Utils.ermittleSchuelerZuVeranstaltung(schuelerListe, veranstaltungsListe);

        //then
        for(Veranstaltung v : resultMap.keySet()){
            assertEquals(3, resultMap.get(v).size());
        }
    }

    /**
     * @author Simon, Jo, Julia
     * Unit-Test für die Methode counter in der Klasse Utils
     */
    @Test
    public void counterTestmitIgnoriertenWuenschen(){
        // given
        Veranstaltung veranstaltung1 = new Veranstaltung();
        veranstaltung1.setId(1);
        veranstaltung1.setMaxSchueler(1);
        veranstaltung1.setMaxVeranstaltungen(1);

        Veranstaltung veranstaltung2 = new Veranstaltung();
        veranstaltung2.setId(2);
        veranstaltung2.setMaxSchueler(1);
        veranstaltung2.setMaxVeranstaltungen(1);

        Veranstaltung veranstaltung3 = new Veranstaltung();
        veranstaltung3.setId(3);
        veranstaltung3.setMaxSchueler(1);
        veranstaltung3.setMaxVeranstaltungen(1);

        ArrayList<Veranstaltung> veranstaltungsListe = new ArrayList<>();
        veranstaltungsListe.add(veranstaltung1);
        veranstaltungsListe.add(veranstaltung2);
        veranstaltungsListe.add(veranstaltung3);

        Schueler schueler1 = new Schueler();
        schueler1.setWahl(new int[]{1, 2, 3, 4, 5, 6});

        Schueler schueler2 = new Schueler();
        schueler2.setWahl(new int[]{2, 1, 3, 4, 5, 6});

        Schueler schueler3 = new Schueler();
        schueler3.setWahl(new int[]{3, 2, 1, 4, 5, 6});

        ArrayList<Schueler> schuelerListe = new ArrayList<>();
        schuelerListe.add(schueler1);
        schuelerListe.add(schueler2);
        schuelerListe.add(schueler3);

        //when
        HashMap<Veranstaltung, List<Schueler>> resultMap= Utils.ermittleSchuelerZuVeranstaltung(schuelerListe, veranstaltungsListe);

        //then
        for(Veranstaltung v : resultMap.keySet()){
            assertEquals(1, resultMap.get(v).size());
        }
    }

    /**
     * @author Simon, Jo, Julia
     * Unit-Test für die Methode ermittleSchuelerOhneWuensche in der Klasse Utils
     */
    @Test
    public void ermittleSchuelerOhneWuenscheTest(){
        //given
        Schueler schueler1 = new Schueler();
        schueler1.setWahl(new int[]{0, 0, 0, 0, 0, 0});

        Schueler schueler2 = new Schueler();
        schueler2.setWahl(new int[]{0, 1, 3, 4, 5, 6});

        Schueler schueler3 = new Schueler();
        schueler3.setWahl(new int[]{3, 2, 1, 4, 5, 6});

        ArrayList<Schueler> schuelerListe = new ArrayList<>();
        schuelerListe.add(schueler1);
        schuelerListe.add(schueler2);
        schuelerListe.add(schueler3);

        //when
        List<Schueler> result = Utils.ermittleSchuelerOhneWuensche(schuelerListe);

        //then
        assertEquals(result.size() ,1);
    }

    /**
     * @author Simon, Jo, Julia
     * Unit-Test für die Methode ermittleSchuelerMitTeilwuenschen in der Klasse Utils
     */
    @Test
    public void ermittleSchuelerMitTeilWuenschenTest(){
        //given
        Schueler schueler1 = new Schueler();
        schueler1.setWahl(new int[]{0, 0, 0, 0, 0, 0});

        Schueler schueler2 = new Schueler();
        schueler2.setWahl(new int[]{0, 1, 3, 4, 5, 6});

        Schueler schueler3 = new Schueler();
        schueler3.setWahl(new int[]{3, 2, 1, 4, 5, 6});

        ArrayList<Schueler> schuelerListe = new ArrayList<>();
        schuelerListe.add(schueler1);
        schuelerListe.add(schueler2);
        schuelerListe.add(schueler3);

        //when
        List<Schueler> result = Utils.ermittleSchuelerMitTeilwuenschen(schuelerListe);

        //then
        assertEquals(result.size() ,2);
    }

    /**
     * @author Maurice, Jan
     */
    @Test
    public void ermittleWunschquote100Prozent() {
        //given
        ArrayList<Schueler> schueler = new ArrayList<>();
        ArrayList<Schueler> tempSchueler = new ArrayList<>();

        schueler.add(new Schueler("Hans", "Peter", "AAA123", new int[] {1,2,3,4,5,6}));
        tempSchueler.add(new Schueler("Hans", "Peter", "AAA123", new int[] {1,2,3,4,5,6}));
        schueler.add(new Schueler("Johan", "Kran", "ABC987", new int[] {2,2,3,4,5,6}));
        tempSchueler.add(new Schueler("Johan", "Kran", "ABC987", new int[] {2,2,3,4,5,6}));
        schueler.add(new Schueler("Fred", "Peter", "AAA123", new int[] {1,2,66,4,5,6}));
        tempSchueler.add(new Schueler("Fred", "Peter", "AAA123", new int[] {1,2,66,4,5,6}));

        try {
            //When
            double score = Utils.scoreBerechnung(tempSchueler, schueler);
            //Then
            assertEquals(100.0, score, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @author Maurice, Jan
     */
    @Test
    public void ermittleWunschquote50Prozent() {
        //given
        ArrayList<Schueler> schueler = new ArrayList<>();
        ArrayList<Schueler> tempSchueler = new ArrayList<>();

        schueler.add(new Schueler("Hans", "Peter", "AAA123", new int[] {1,2,3,4,5,6}));
        tempSchueler.add(new Schueler("Hans", "Peter", "AAA123", new int[] {1,2,3,4,5,6}));
        schueler.add(new Schueler("Johan", "Kran", "ABC987", new int[] {5552,145,123,8,7,2}));
        tempSchueler.add(new Schueler("Johan", "Kran", "ABC987", new int[] {2,2,3,4,5,6}));

        try {
            //When
            double score = Utils.scoreBerechnung(tempSchueler, schueler);
            //Then
            assertEquals(50.0, score, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @author Maurice, Jan
     */
    @Test
    public void ermittleWunschquote100ProzentLeereWuensche() {
        //given
        ArrayList<Schueler> schueler = new ArrayList<>();
        ArrayList<Schueler> tempSchueler = new ArrayList<>();

        schueler.add(new Schueler("Hans", "Peter", "AAA123", new int[] {1,2,3,4,5,6}));
        tempSchueler.add(new Schueler("Hans", "Peter", "AAA123", new int[] {1,2,3,4,5,6}));
        schueler.add(new Schueler("Johan", "Kran", "ABC987", new int[] {0,0,0,0,0,0}));
        tempSchueler.add(new Schueler("Johan", "Kran", "ABC987", new int[] {2,2,3,4,5,6}));

        try {
            //When
            double score = Utils.scoreBerechnung(tempSchueler, schueler);
            //Then
            assertEquals(100.0, score, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
