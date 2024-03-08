package algorithmus;

import datenmodelle.Schueler;
import datenmodelle.Veranstaltung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Simon, Jo, Julia
 */
public class Utils {


    /**
     * @author Simon, Jo, Julia
     * @param schuelerListe
     * @param veranstaltungsListe
     * @return HashMap<Veranstaltung, List<Schueler>>
     *
     *     diese Methode zählt, wie viele Schueler sich die Teilnahme an einer bestimmten Veranstaltung gewünscht haben
     *     Schuelerwuensche, bei denen die maximale Kapazität erreicht wurde, werden ignoriert
     */
    public static HashMap<Veranstaltung, List<Schueler>> counter(List<Schueler> schuelerListe, List<Veranstaltung> veranstaltungsListe){
        HashMap<Veranstaltung, List<Schueler>> resultMap = new HashMap<>();

        for(int i = 0; i < 6; i++){
            for(Veranstaltung veranstaltung : veranstaltungsListe){
                ArrayList<Schueler> tempSchuelerList = new ArrayList<>();
                for(Schueler schueler : schuelerListe){
                    if(veranstaltung.getId() == schueler.getWahl()[i] && tempSchuelerList.size() < veranstaltung.ermittleMaxkapazitaet()){
                        tempSchuelerList.add(schueler);
                    }
                }
                if(tempSchuelerList.size() > 0){
                    resultMap.put(veranstaltung, tempSchuelerList);
                }
            }
        }

        return resultMap;
    }
}
