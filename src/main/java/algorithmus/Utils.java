package algorithmus;

import datenmodelle.Schueler;
import datenmodelle.Timeslot_Enum;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * @author Simon, Jo, Julia, Jan, Maurice
 */
public class Utils {


    /**
     * author Julia
     * @param List<Veranstaltungen> veranstaltungen
     * @return HashMap<Veranstaltung, List<Zuordnung>>
     *     Diese Methode erstellt eine HashMap, bei der zu jedem Unternehmen die Liste aller möglichen Zuordnungen (Zeitslots, Veranstaltung)
     *     gespeichert werden
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
     * @author Simon, Jo, Julia
     * @param schuelerListe
     * @param veranstaltungsListe
     * @return HashMap<Veranstaltung, List<Schueler>>
     *
     *     diese Methode zählt, wie viele Schueler sich die Teilnahme an einer bestimmten Veranstaltung gewünscht haben
     *     Schuelerwuensche, bei denen die maximale Kapazität erreicht wurde, werden ignoriert
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
     * @author Simon, Jo, Julia
     * @param schuelerListe
     * @return List<Schueler>
     * Diese Methode prueft alle Schueler und ermittelt jene Schueler, die keine Wuensche haben
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
     * @author Simon, Jo, Julia
     * @param schuelerListe
     * @return
     * Diese Methode ermittelt alle Schueler, weniger als 6 Wuensche geaeussert haben
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
     * @author Simon, Julia
     * @param List<Zuordnung> zuordnungen
     * @return HashMap<Zuordnung, Boolean> result
     * Wenn die Mindestkapazitaet (minSchueler der Veranstaltung der Zuordnung) nicht erfuellt ist,
     * wird false gesetzt.
     * Wenn die Mindestkapazität erfuellt ist, wird true gesetzt.
     */
    public static HashMap<Zuordnung, Boolean> ueberpruefeAuslastungZuordnungen(List<Zuordnung> zuordnungen) {
        HashMap<Zuordnung, Boolean> result = new HashMap<>();
        for(Zuordnung zo : zuordnungen) {
            if(zo.getKapazität() < zo.getVeranstaltung().getMinSchueler()) {
                result.put(zo, false);
            } else {
                result.put(zo, true);
            }
        }
        return result;
    }
}
