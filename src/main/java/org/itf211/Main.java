package org.itf211;


import datenEinlesen.SchuelerWuensche;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            SchuelerWuensche.auslesen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}