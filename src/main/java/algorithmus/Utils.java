package algorithmus;

import datenmodelle.Schueler;
import datenmodelle.Timeslot_Enum;
import datenmodelle.Veranstaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * @author Simon, Jo, Julia, Jan, Maurice
 */
public class Utils {
    /**
     * @author Maurice, Jan
     * @param unternehmenList
     *
     * Wird nach dem Counter ausgeführt.
     * Passt die anzahl der Timeslots an für ein Unternehmen. Aufgrund der Mindestanzahl an Schuelern für einen Timeslot wird
     * festgestellt ob der Timeslot entfernt wird oder nicht. Im fall das ein Timeslot entfernt wird, werden die bisherigen Schueler
     * wieder mit dem Autofill behandelt.
     *
     * @TODO Muss noch mit richtigen Daten getestet werden.
     */
    public void manageTimeslots(List<Veranstaltung> unternehmenList) {
        List<Schueler> autofillListe = new ArrayList<>();
        List<Timeslot_Enum> removableTimeslots = new ArrayList<>();

        for (Veranstaltung unternehmen : unternehmenList) {
        //Geht einmal jeden möglichen Timeslot des Unternehmens durch
            unternehmen.getTimeslotReservierung()
                .forEach((timeslotEnum, schueler) -> {
                    //Checkt ob für diesen Timeslot genug Schueler vorhanden sind
                    if(schueler.size() < unternehmen.getMinSchueler()) {
                        //Behandlung der schueler vor der Aufloesung des Timeslots -> Schueler wunsch neu vergeben
                        unternehmen.getTimeslotReservierung()
                                .get(timeslotEnum)
                                .forEach(s -> {
                                    s.getStundenplan()
                                            .stream()
                                            .filter(z -> z.getZeitpunkt().equals(timeslotEnum));
                                    autofillListe.add(s);
                                });
                        removableTimeslots.add(timeslotEnum);
                    }
                });
            //Entfernt die nicht gebrauchten timeslots von dem Unternehmen
            for(Timeslot_Enum timeslot : removableTimeslots) {
                unternehmen.getTimeslotReservierung().remove(timeslot);
            }
            //Leert die zu entfernenden Timeslots für das nächste Unternehmen
            removableTimeslots.clear();
        }
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
    public static HashMap<Veranstaltung, List<Schueler>> counter(List<Schueler> schuelerListe, List<Veranstaltung> veranstaltungsListe){
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
     * @author
     */
    //@TODO mit funktion füllen.
    public void autoFillAbarbeiten(List<Schueler> schueler){
        for(Schueler s : schueler) {

        }
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
}
