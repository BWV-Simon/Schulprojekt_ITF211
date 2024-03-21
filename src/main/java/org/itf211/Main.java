package org.itf211;


import algorithmus.Utils;
import algorithmus.WahlenZuordnen;
import datenEinlesen.SchuelerWuensche;
import datenEinlesen.VorhandeneVeranstaltungen;
import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;
import datenmodelle.Zuordnung;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class
Main {
    public static void main(String[] args) {

        try {
            List<Veranstaltung> vliste = VorhandeneVeranstaltungen.erstellenVeranstaltungen();
            List<Schueler> original = SchuelerWuensche.auslesen();
            List<Schueler> sliste = SchuelerWuensche.auslesen();
            HashMap<Veranstaltung, List<Zuordnung>> result = WahlenZuordnen.zuordnungWahlen(sliste, vliste);
            Utils.scoreBerechnung(sliste, original);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}