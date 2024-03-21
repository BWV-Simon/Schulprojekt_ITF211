package org.itf211;


import algorithmus.Utils;
import algorithmus.WahlenZuordnen;
import datenAusgeben.AusgabeSchueler;
import datenAusgeben.AusgabeUnternehmen;
import datenEinlesen.DateiKonvertieren;
import datenEinlesen.SchuelerWuensche;
import datenEinlesen.VorhandeneVeranstaltungen;
import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<Veranstaltung> vliste = VorhandeneVeranstaltungen.erstellenVeranstaltungen();
            List<Schueler> original = SchuelerWuensche.auslesen();
            List<Schueler> sliste = SchuelerWuensche.auslesen();
            HashMap<Veranstaltung, List<Zuordnung>> result = WahlenZuordnen.zuordnungWahlen(sliste, vliste);
            double score = Utils.scoreBerechnung(sliste, original);
            List<Zuordnung> zListe = new ArrayList<>();
            for(Veranstaltung v : result.keySet()) {
                List<Zuordnung> temp = result.get(v);
                for(Zuordnung z : temp) {
                    zListe.add(z);
                }
            }
            AusgabeSchueler.SchuelerListenErstellen(sliste, zListe, score);
            AusgabeUnternehmen.zuordnungErstellen(zListe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}