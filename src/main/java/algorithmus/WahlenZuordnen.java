package algorithmus;

import datenmodelle.Schueler;
import datenmodelle.Timeslot_Enum;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Julia Hemkendreis
 */
public class WahlenZuordnen {

    public static HashMap<Veranstaltung,List<Zuordnung>> zuordnungWahlen(List<Schueler> schuelerWahlen, List<Veranstaltung> veranstaltungList) {
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
        System.out.println("Start");
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
                                        System.err.println(s.getNachname() + " konnte nicht zugeordnet werden " + i);
                                        break;
                                    }
                                }
                            } else if(schuelerwunsch == 0) {
                            if(!schuelerAutofill.contains(s)) {
                                System.out.println(s.getNachname() + " hat keinen Wunsch angegeben " + i);
                                schuelerAutofill.add(s);
                            }
                            break;
                        }
                    }
                }
            }
        }
        autofillSchueler(schuelerAutofill, veranstaltungenSlots);
        System.out.println("Ende");
        return veranstaltungenSlots;
    }

    private static void zuordnenSchueler(Zuordnung zo, Schueler s, int wahlSlot, int schuelerwunsch) {
        zo.setKapazitaet(zo.getKapazitaet() - 1);
        zo.setKapazitaet(zo.getKapazitaet() - 1);
        zo.addSchueler(s);
        s.addStunden(zo.getZeitpunkt());
        s.setWahl(wahlSlot, schuelerwunsch);
        System.out.println(s.getNachname() + " wurde für " + zo.getZeitpunkt() + " bei " + zo.getVeranstaltung().getUnternehmen() + " für den Wunsch: " + wahlSlot + " zugeordnet.");

    }

    /** Ueberladene Methode zum Zuordnen von Schuelern via Autofill
     * Jan & Maurice
     * @param zo
     * @param s
     * @param schuelerwunsch
     */
    private static void zuordnenSchueler(Zuordnung zo, Schueler s, int schuelerwunsch) {
        zo.setKapazitaet(zo.getKapazitaet() - 1);
        s.addStunden(zo.getZeitpunkt());
        zo.addSchueler(s);
        System.out.println(s.getNachname() + " wurde für " + zo.getZeitpunkt() + " bei " + zo.getVeranstaltung().getUnternehmen() + " zugeordnet. via Autofill");
    }
    /**
     * @author Jan & Maurice
     * Autofill-Behandlung der noch offenen Schuelerslots
     */
    public static void autofillSchueler(List<Schueler> schuelerList, HashMap<Veranstaltung, List<Zuordnung>> veranstaltungenSlots) {
        for (Schueler s : schuelerList) {
            for (Veranstaltung v : veranstaltungenSlots.keySet()) {
                for (Zuordnung zo : veranstaltungenSlots.get(v)) {
                    if(zo.getKapazitaet()>0 && (!s.getStunden().contains(zo.getZeitpunkt()))){
                        zuordnenSchueler(zo, s, -1);
                        break;
                    }
                }
                if(s.getStunden().size()>=5){
                    break;
                }
            }
        }
    }
}
