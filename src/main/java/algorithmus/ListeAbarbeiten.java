package algorithmus;

import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *@author Maurice & Jan & Simon
 */
public class ListeAbarbeiten {
    private ArrayList<Schueler> schueler;
    private ArrayList<Veranstaltung> unternehmen;
    private HashMap<Schueler, int[]> schuelerWahlen;
    private double wunschQuote;

    ListeAbarbeiten(ArrayList<Schueler> schueler, ArrayList<Veranstaltung> unternehmen){
        this.schueler=schueler;
        this.unternehmen=unternehmen;
    }

    public ArrayList<Schueler> getSchueler() {
        return schueler;
    }

    public void setSchueler(ArrayList<Schueler> schueler) {
        this.schueler = schueler;
    }

    public ArrayList<Veranstaltung> getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(ArrayList<Veranstaltung> unternehmen) {
        this.unternehmen = unternehmen;
    }

    //ToDo Methoden implementieren
    //maxScore = 20 am Ende hinzufuegen -> Einbringen nach Algorithmus
    //Score bei Autofill-Abarbeitung addieren -> Einbringen nach Algorithmus
    //Berechnung der Wunschquote: SchuelerScores / (AnzahlSchueler*20) -> Einbringen nach Algorithmus

    /**
     * @author Maurice & Jan & Simon
     */
    public void ErsteWuenscheAbarbeiten(){
        //Jedes unternehmen durchgehen
        for (Veranstaltung unternehmen : this.unternehmen) {
            //Alle wünsche durchgehen
            for(int i = 0; i < 5; i++) {
                //Alle Schueler durchgehen
                for(Schueler schueler : this.schueler) {
                    int[] schuelerZuordnung = new int [5];
                    schuelerWahlen.put(schueler, schuelerZuordnung);
                    //Check ob der schueler bei diesem wunsch das Unternehmen hat.

                    if(schueler.getWahl()[i] == unternehmen.getId()) {
                        //ToDo Hinzufuegen eines Schuelers beim unternehmen
                    }
                }
            }
        }
    }

    /**
     * @author Maurice & Jan & Simon
     */
    public void letzteWuenscheAbarbeiten(){

    }

    /**
     * @author Maurice & Jan & Simon
     * ToDo Zukuenftig Score berechnung bei Autofill hinzufuegen
     */
    public void autoFillAbarbeiten(){

    }

    /**
     * @author Simon & Maurice
     * diese Methode erstellt initial die Hashmap und befüllt diese mit allen Schülern und einem zugehörigen leeren Array der Länge 5
     * in diesem Array soll die Zuordnung eines Schülers zu einer Veranstaltung festgehalten werden
     */

    //TODO: hierzu einen Test erstellen und die Methode in der oberen Verarbeitung einbinden
    public void hashMapVorbereiten(){
        schuelerWahlen = new HashMap<>();
        for(Schueler schueler : this.schueler){
            int[] zuordnung = new int[5];
            schuelerWahlen.put(schueler, zuordnung);
        }
    }
}
