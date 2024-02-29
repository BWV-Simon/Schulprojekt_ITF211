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

    ListeAbarbeiten(ArrayList<Schueler> schueler, ArrayList<Veranstaltung> unternehmen){

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
}
