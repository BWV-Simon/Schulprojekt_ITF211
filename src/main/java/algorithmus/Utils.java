package algorithmus;

import datenmodelle.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * @author Simon, Jo, Julia, Jan & Maurice
 */
public class Utils {

    /**
     * Diese Methode erstellt eine HashMap, bei der zu jedem Unternehmen,
     * die Liste aller moeglichen Zuordnungen (Zeitslots, Veranstaltung) gespeichert werden
     * @author Julia
     * @param List<Veranstaltungen> veranstaltungen
     * @return HashMap<Veranstaltung, List<Zuordnung>>
     */
    public static HashMap<Veranstaltung, List<Zuordnung>> ermittleAlleSlotsZuVeranstaltung(List<Veranstaltung> veranstaltungen) {
        HashMap<Veranstaltung, List<Zuordnung>> result = new HashMap<>();
        for(Veranstaltung v : veranstaltungen) {
            List<Zuordnung> temp = new ArrayList<>();
            List<Timeslot_Enum> slots = v.createTimeSlotListe();
            for(Timeslot_Enum e : slots) {
                Zuordnung zo = new Zuordnung(e, v);
                temp.add(zo);
            }
            result.put(v, temp);
        }
        return result;
    }

    /**
     * Diese Methode zaehlt, wie viele Schueler sich die Teilnahme an einer bestimmten Veranstaltung gewuenscht
     * haben Schuelerwuensche, bei denen die maximale Kapazitaet erreicht wurde, werden ignoriert
     * @author Simon, Jo & Julia
     * @param schuelerListe
     * @param veranstaltungsListe
     * @return HashMap<Veranstaltung, List<Schueler>>
     */
    public static HashMap<Veranstaltung, List<Schueler>> ermittleSchuelerZuVeranstaltung(List<Schueler> schuelerListe, List<Veranstaltung> veranstaltungsListe){
        HashMap<Veranstaltung, List<Schueler>> resultMap = new HashMap<>();
        for(int i = 0; i < 6; i++){
            for(Veranstaltung veranstaltung : veranstaltungsListe){
                ArrayList<Schueler> tempSchuelerList = new ArrayList<>();
                for(Schueler schueler : schuelerListe){
                    if(veranstaltung.getId() == schueler.getWahl()[i] && tempSchuelerList.size() < veranstaltung.ermittleMaxkapazitaet()){
                        tempSchuelerList.add(schueler);
                    }
                }
                if(tempSchuelerList.size() > 0){
                    resultMap.put(veranstaltung, tempSchuelerList);
                }
            }
        }
        return resultMap;
    }

    /**
     * Diese Methode prueft alle Schueler und ermittelt jene Schueler, die keine Wuensche haben
     * @author Simon, Jo & Julia
     * @param schuelerListe
     * @return List<Schueler>
     */
    public static List<Schueler> ermittleSchuelerOhneWuensche(List<Schueler> schuelerListe){
        boolean wunschGefunden = false;
        ArrayList<Schueler> result = new ArrayList<>();

        for (Schueler s : schuelerListe){
            for (int wunsch : s.getWahl()){
                if(wunsch != 0){
                    wunschGefunden = true;
                }
            }
            if(!wunschGefunden){
                result.add(s);
            }
            wunschGefunden = false;
        }
        return result;
    }

    /**
     * Diese Methode ermittelt alle Schueler, weniger als 6 Wuensche geaeussert haben
     * @author Simon, Jo & Julia
     * @param schuelerListe
     * @return
     */
    public static List<Schueler> ermittleSchuelerMitTeilwuenschen(List<Schueler> schuelerListe){
        ArrayList<Schueler> result = new ArrayList<>();

        for (Schueler s : schuelerListe){
            for (int wunsch : s.getWahl()){
                if(wunsch == 0){
                    result.add(s);
                    break;
                }
            }
        }
        return result;
    }

    /** Methode, die die Auslastung von Zuordnungen auf die Mindestanzahl an teilnehmenden Schuelern ueberprueft
     * @author Simon & Julia
     * @param List<Zuordnung> zuordnungen
     * @return HashMap<Zuordnung, Boolean> result
     * Wenn die Mindestkapazitaet (minSchueler der Veranstaltung der Zuordnung) nicht erfuellt ist,
     * wird false gesetzt.
     * Wenn die Mindestkapazitaet erfuellt ist, wird true gesetzt.
     */
    public static HashMap<Zuordnung, Boolean> ueberpruefeAuslastungZuordnungen(List<Zuordnung> zuordnungen) {
        HashMap<Zuordnung, Boolean> result = new HashMap<>();
        for(Zuordnung zo : zuordnungen) {
            if(zo.getKapazitaet() < zo.getVeranstaltung().getMinSchueler()) {
                result.put(zo, false);
            } else {
                result.put(zo, true);
            }
        }
        return result;
    }

    /** Berechnet die Erfolgsquote des Algorithmus
     * @author Maurice & Jan
     * @param tempSchueler
     * @param original
     * @return double endScore(nach 2 Nachkommastellen abgeschnitten)
     * @throws IOException
     */
    public static double scoreBerechnung(List<Schueler> tempSchueler, List<Schueler> original) throws IOException {
        double maxScore = 0;
        double wirklicherScore = 0;
        for (Schueler s : original) {
            //schuelerMaxPunktzahl nur zur Kontrolle der Wahlen da
            int schuelerMaxPunktzahl = 0;
            int aktuellerSchuelerScore = 0;
            for (int i = 0; i < 6 && !(schuelerMaxPunktzahl >= 20); i++) {
                //legt die Maximale Punktzahl fest
                if (s.getWahl()[i] != 0) {
                    maxScore = maxScore + 6 - i;
                    schuelerMaxPunktzahl = schuelerMaxPunktzahl + 6 - i;
                }
            }
            for (Schueler ts : tempSchueler) {
                if (ts.getKlasse().equals(s.getKlasse()) &&
                        ts.getNachname().equals(s.getNachname()) &&
                        ts.getVorname().equals(s.getVorname())) {
                    for (int i = 0; i < 6 && aktuellerSchuelerScore < schuelerMaxPunktzahl; i++) {
                        //legt die Maximale Punktzahl fest
                        if (s.getWahl()[i] == ts.getWahl()[i] && s.getWahl()[i] != 0) {
                            wirklicherScore = wirklicherScore + 6 - i;
                            aktuellerSchuelerScore = aktuellerSchuelerScore + 6 - i;
                        }
                    }
                    break;
                }
            }
        }
        //Ergebnis wird nach 2 Nachkommastellen abgeschnitten
        double endScore = (wirklicherScore / maxScore) * 100;
        DecimalFormat df = new DecimalFormat("#,##");
        df.applyPattern("#.##");
        return Double.parseDouble(df.format(endScore).replace(',','.'));
    }

    /** Die Methode teilt die Raeume zu den Veranstaltungen zu
     * @author Julia, Maurice
     * @param zuordnung
     * @param raeume
     */
    public static void zuteilungRaume(HashMap<Veranstaltung, List<Zuordnung>> zuordnung, List<Raum> raeume) {
        int i = 0;
        List<Zuordnung> keineRaume = new ArrayList<>();
        for(Veranstaltung v : zuordnung.keySet() ) {
            List<Zuordnung> temp_z = zuordnung.get(v);
            for(Zuordnung z: temp_z) {
                 if(z.getSchuelerList().size() > 0) {
                     for(int x = 0; x < raeume.size(); x++) {
                         if (i == 0) {
                             if (z.getSchuelerList().size() < raeume.get(x).getKapzität()) {
                                 z.setRaumNr(raeume.get(x));
                                 raeume.get(x).addZeitpunkt(z.getZeitpunkt());
                                 break;
                             }
                         } else if (z.getSchuelerList().size() < raeume.get(x).getKapzität() && (!raeume.get(x).getZeiten().contains(z.getZeitpunkt()))) {
                             z.setRaumNr(raeume.get(x));
                             raeume.get(x).addZeitpunkt(z.getZeitpunkt());
                             break;
                         }
                     }
                     if(z.getRaumNr() == null) {
                         keineRaume.add(z);
                     }
                 }
            }
            i++;
        }
        for(Zuordnung z : keineRaume) {
            if(z.getSchuelerList().size() > 0) {
                Raum dummy = new Raum("Dummy", 20);
                z.setRaumNr(dummy);
            }
        }
    }
}
