package algorithmus;

import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;

import java.util.ArrayList;

/**
 *@author Maurice & Jan & Simon
 */
public class ListeAbarbeiten {
    private ArrayList<Schueler> schueler;
    private ArrayList<Veranstaltung> unternehmen;
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
     * @author Maurice & Jan
     */
    public void ErsteWuenscheAbarbeiten(){
        //Jedes unternehmen durchgehen
        for (Veranstaltung unternehmen : this.unternehmen) {
            //Alle wünsche durchgehen
            for(int i = 0; i < 5; i++) {
                //Alle Schueler durchgehen
                for(Schueler schueler : this.schueler) {
                    //Check ob der schueler bei diesem wunsch das Unternehmen hat.
                    if(schueler.getWahl()[i] == unternehmen.getId()) {
                        //ToDo Hinzufuegen eines Schuelers beim unternehmen
                    }
                }
            }
        }
    }

    /**
     * @author Maurice & Jan
     */
    public void letzteWuenscheAbarbeiten(){

    }

    /**
     * @author Maurice & Jan
     * ToDo Zukuenftig Score berechnung bei Autofill hinzufuegen
     */
    public void autoFillAbarbeiten(){

    }
}
