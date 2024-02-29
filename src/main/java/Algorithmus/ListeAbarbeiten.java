package Algorithmus;

import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;

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
    //maxScore = 20 am Ende hinzufuegen
    //Score bei Autofill-Abarbeitung addieren
    //Berechnung der Wunschquote: SchuelerScores / (AnzahlSchueler*20)

    public void ErsteWuenscheAbarbeiten(){

    }
}
