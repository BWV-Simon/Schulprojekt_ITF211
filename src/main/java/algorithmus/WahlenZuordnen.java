package algorithmus;

import datenmodelle.Raum;
import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Julia, Simon, Jan, Maurice & Jo
 */
public class WahlenZuordnen {

    /** Hauptmethode fuer den Algorithmus
     *  Verteilung der Wahlen mit entsprechender Utilisierung der weiteren Methoden (autofill)
     * @author Simon, Julia & Jo
     * @param schuelerWahlen
     * @param veranstaltungList
     * @return HashMap<Veranstaltung,List<Zuordnung>>
     */
    public static HashMap<Veranstaltung,List<Zuordnung>> zuordnungWahlen(List<Schueler> schuelerWahlen, List<Veranstaltung> veranstaltungList, List<Raum> raumList) {
        List<Schueler> tempSchueler = new ArrayList<>();
        //Alle moeglichen Veranstaltungsslots zu den teilnehmenden Unternehmen anlegen und zwischenspeichern
        HashMap<Veranstaltung,List<Zuordnung>> veranstaltungenSlots = Utils.ermittleAlleSlotsZuVeranstaltung(veranstaltungList);
        // Liste an Schueler fuer die zufaellige Zuordnung anhand der Schueler ohne Wuensche erstellen
        List<Schueler> schuelerAutofill = Utils.ermittleSchuelerOhneWuensche(schuelerWahlen);

        //Liste der Schueler von den Schuelern, die keine Wuensche haben bereinigen
        for (Schueler s : schuelerWahlen) {
            if (!schuelerAutofill.contains(s)) {
                tempSchueler.add(s);
            }
        }
        // Zuordnung der Schueler nach Wuenschen und zufaellig angeordnet
        for (int i = 0; i < 6; i++) {
            Collections.shuffle(tempSchueler);
            for (Schueler s : tempSchueler) {
                if (s.getStunden().size() < 5) {
                    int schuelerwunsch = s.getWahl()[i];
                    for(Veranstaltung v : veranstaltungList) {
                        if(v.getId() == schuelerwunsch) {
                            for(int x = 0; x < veranstaltungenSlots.get(v).size(); x++) {
                                if( i == 0) {
                                    zuordnenSchueler(veranstaltungenSlots.get(v).get(x), s, i, schuelerwunsch);
                                    break;
                                } else if((!s.getStunden().contains(veranstaltungenSlots.get(v).get(x).getZeitpunkt())) && veranstaltungenSlots.get(v).get(x).getKapazitaet() > 0) {
                                    zuordnenSchueler(veranstaltungenSlots.get(v).get(x), s, i, schuelerwunsch);
                                    break;
                                } else if(x == veranstaltungenSlots.get(v).size() - 1) {
                                        s.setWahl(i, 0);
                                        schuelerAutofill.add(s);
                                        break;
                                    }
                                }
                            } else if(schuelerwunsch == 0) {
                            if(!schuelerAutofill.contains(s)) {
                                schuelerAutofill.add(s);
                            }
                            break;
                        }
                    }
                }
            }
        }
        autofillSchueler(schuelerAutofill, veranstaltungenSlots);
        /**
         * Code ist ausnahmsweise auskommentiert, da diese Funktion nach Absprache vorerst deaktiviert wurde,
         * jedoch bei Bedarf verwendet werden kann.
         * @<code>
         *     checkVeranstaltungskapazitaeten(veranstaltungenSlots);
         * </code>
         */
        checkVeranstaltungskapazitaeten(veranstaltungenSlots);
        Utils.zuteilungRaume(veranstaltungenSlots, raumList);
        return veranstaltungenSlots;
    }

    /** Methode, die zu einer uebergebenen Zuordnung den entsprechenden Schueler hinzufuegt und den Schuelerwunsch in dem
     * Wahlslot setzt und den Zeitslot fuer den Schueler zwischenspeichert
     * @author Simon & Julia
     * @param zo
     * @param s
     * @param wahlSlot
     * @param schuelerwunsch
     */
    private static void zuordnenSchueler(Zuordnung zo, Schueler s, int wahlSlot, int schuelerwunsch) {
        zo.setKapazitaet(zo.getKapazitaet() - 1);
        zo.setKapazitaet(zo.getKapazitaet() - 1);
        zo.addSchueler(s);
        s.addStunden(zo.getZeitpunkt());
        s.setWahl(wahlSlot, schuelerwunsch);
    }

    /** Ueberladene Methode zum Zuordnen von Schuelern via Autofill
     * @author Jan & Maurice
     * @param zo
     * @param s
     */
    private static void zuordnenSchueler(Zuordnung zo, Schueler s) {
        zo.setKapazitaet(zo.getKapazitaet() - 1);
        zo.addSchueler(s);
        s.addStunden(zo.getZeitpunkt());
        for(int i = 0; i < s.getWahl().length; i++) {
            if (s.getWahl()[i] == 0) {
                s.setWahl(i, zo.getVeranstaltung().getId());
                break;
            }
        }
    }

    /**
     * @author Jan & Maurice
     * @param schuelerList
     * @param veranstaltungenSlots
     * Autofill-Behandlung der noch offenen Schuelerslots
     */
    public static void autofillSchueler(List<Schueler> schuelerList, HashMap<Veranstaltung, List<Zuordnung>> veranstaltungenSlots) {
        for (Schueler s : schuelerList) {
            for (Veranstaltung v : veranstaltungenSlots.keySet()) {
                for (Zuordnung zo : veranstaltungenSlots.get(v)) {
                    if(zo.getKapazitaet()>0 && (!s.getStunden().contains(zo.getZeitpunkt()))){
                        zuordnenSchueler(zo, s);
                        break;
                    }
                }
                if(s.getStunden().size()>=5){
                    break;
                }
            }
        }
    }

    public static void autofillSchueler(Schueler s, HashMap<Veranstaltung, List<Zuordnung>> veranstaltungenSlots) {
            for (Veranstaltung v : veranstaltungenSlots.keySet()) {
                for (Zuordnung zo : veranstaltungenSlots.get(v)) {
                    if(zo.getKapazitaet()>0 && (!s.getStunden().contains(zo.getZeitpunkt()))){
                        zuordnenSchueler(zo, s);
                        break;
                    }
                }
                if(s.getStunden().size()>=5){
                    break;
                }
            }
    }

    /** Methode zur Sicherstellung, dass nur Veranstaltungen, deren Mindestkapazität erfuellt wurde stattfinden
     * Schueler deren Wunschveranstaltung nicht stattfinden kann werden autofilled
     * Zuordnungen, die nicht genug Schueler haben,werden nicht mehr beruecksichtigt (Kapazitaet auf 0 gesetzt und
     * Schueler werden entfernt)
     * @author Simon & Julia
     * @param input
     */
    public static void checkVeranstaltungskapazitaeten(HashMap<Veranstaltung,List<Zuordnung>> input){
        ArrayList<Schueler> schuelerAutoFill = new ArrayList<>();
        for(Veranstaltung v : input.keySet()){
            for (Zuordnung zo : input.get(v)){
                Boolean result = Utils.ueberpruefeAuslastungZuordnungen(zo);
                if(!result){
                    for(Schueler s : zo.getSchuelerList()){
                        s.deleteStunde(zo.getZeitpunkt());
                        for(int i = 0; i < s.getWahl().length; i++){
                            if(s.getWahl()[i] == zo.getVeranstaltung().getId()){
                                s.setWahl(i, 0);
                                if(!schuelerAutoFill.contains(s)) {
                                    schuelerAutoFill.add(s);
                                }
                            }
                        }
                    }
                    autofillSchueler(schuelerAutoFill, input);
                    schuelerAutoFill.clear();
                    zo.getSchuelerList().clear();
                    zo.setKapazitaet(0);
                }
            }
        }
    }
}
