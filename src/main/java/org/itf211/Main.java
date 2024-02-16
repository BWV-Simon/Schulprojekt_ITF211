package org.itf211;


import datenEinlesen.DateiKonvertieren;
import datenEinlesen.SchuelerWuensche;
import datenmodelle.Schueler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<Schueler> liste = SchuelerWuensche.auslesen();

            for(Schueler s : liste){
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}