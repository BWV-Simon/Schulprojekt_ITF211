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

    public static void zuordnungWahlen(List<Schueler> schuelerWahlen, List<Veranstaltung> veranstaltungList) {
        List<Schueler> tempSchueler = new ArrayList<>();
        //Alle moeglichen Veranstaltungsslots zu den teilnehmenden Unternehmen anlegen und zwischenspeichern
        HashMap<Veranstaltung,List<Zuordnung>> veranstaltungenSlots = Utils.ermittleAlleSlotsZuVeranstaltung(veranstaltungList);
        // Liste an Schueler fuer die zufaellige Zuordnung anhand der Schueler ohne Wuensche erstellen
        List<Schueler> schuelerAutofill = Utils.ermittleSchuelerOhneWuensche(schuelerWahlen);

        //Liste der Schueler von den Schuelern, die keine Wuensche haben bereinigen
        for (Schueler s : schuelerWahlen) {
            if(!schuelerAutofill.contains(s)) {
                tempSchueler.add(s);
            }
        }
        System.out.println(tempSchueler.size());
        // Zuordnung der Schueler nach Wuenschen und zufaellig angeordnet
        for( int i = 0; i < 6 ; i++) {
            Collections.shuffle(tempSchueler);
            for(Schueler s : tempSchueler) {
                if(s.getStunden().size() < 5) {
                    int schuelerwunsch = s.getWahl()[i];
                    for(Veranstaltung v : veranstaltungList) {
                        if(v.getId() == schuelerwunsch) {
                            /** TODO Liste an Zuordnungen auf oberster Methodenebene anlegen, damit wir diese ueberpruefen
                             *  koennen und die Schueler hinzufuegen koennen
                             */
                            List<Zuordnung> moeglichkeiten = veranstaltungenSlots.get(v);
                            for(int x = 0; x < moeglichkeiten.size(); x++) {
                                Zuordnung zo = moeglichkeiten.get(x);
                                if( i == 0) {
                                    zuordnenSchueler(zo, s, i, schuelerwunsch);
                                    break;
                                } else if((!s.getStunden().contains(zo.getZeitpunkt())) && zo.getKapazität() > 0) {
                                    zuordnenSchueler(zo, s, i, schuelerwunsch);
                                    break;
                                } else if(x == moeglichkeiten.size() - 1) {
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
    }

    private static void zuordnenSchueler(Zuordnung zo, Schueler s, int wahlSlot, int schuelerwunsch) {
        zo.setKapazität(zo.getKapazität() - 1);
        s.addStunden(zo.getZeitpunkt());
        s.setWahl(wahlSlot, schuelerwunsch);
        System.out.println(s.getNachname() + " wurde für " + zo.getZeitpunkt() + " bei " + zo.getVeranstaltung().getUnternehmen() + " für den Wunsch: " + wahlSlot + " zugeordnet.");

    }
}
