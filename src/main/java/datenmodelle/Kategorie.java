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

    /** Diese Methode ermittelt die entsprechende Kategorie
     * @param kategorie ; String, der in der Excel-Datei ausgelesen wird
     * @return Enum für die Objekterstellung
     */
    public static Kategorie ermittleKategorie(String kategorie) {
        switch (kategorie) {
            case "kaufmännisch":
                return Kategorie.KAUFMAENNISCH;
            case "Verwaltung":
                return Kategorie.VERWALTUNG;
            case "Studium":
                return Kategorie.STUDIUM;
            case "Studierende":
                return Kategorie.STUDIERENDE;
            default:
                throw new IllegalArgumentException("Diese Kategorie gibt es nicht.");
        }
    }



}
