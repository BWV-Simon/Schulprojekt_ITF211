package datenmodelle;

/**
 * @author Julia Hemkendreis & Jo Duras
 */
public enum Kategorie {
    KAUFMAENNISCH("Kaufmaennische Ausbildung und duales Studium im Unternehmen"),
    VERWALTUNG("Ausbildung oder duales Studium in der Verwaltung"),
    STUDIUM("Studium"),
    STUDIERENDE("Studierende stellen ihr Studium vor");

    private final String text;

    Kategorie(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }


}
