import org.json.JSONException;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, JSONException {
        WikidataAPI wikidataAPI = new WikidataAPI();
        //Test des méthodes

        System.out.println(wikidataAPI.searchEntity("Paris"));
        System.out.println(wikidataAPI.getEntity("Q90"));

        //Test avec un fichier de configuration
        String[] temp;
        String delimiter = ",";
        List<String> lObj = new ArrayList<String>();

        try {
            InputStream flux = new FileInputStream("MatrixSynthesizerWikipedia/File/FichierConfig.txt");
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            while ((ligne = buff.readLine()) != null) {
                temp = ligne.split(delimiter);
                for (int i = 0; i < temp.length; i++) {
                    int p1 = temp[i].indexOf('(');
                    int p2 = temp[i].indexOf(')');
                    lObj.add(temp[i].substring(p1 + 1, p2));
                }
            }
            buff.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //Récupération des données à partir des clés obtenues dans le fichier de configuration
        String dataCsv = "";
        for (String str : lObj) {
            dataCsv += str + "\n";
        }


        //////********* PARTIE : WIKIPEDIA ********//////
        WikipediaAPI wikipediaAPI = new WikipediaAPI();

        //Ajout des différentes recherches
        String name = "Lorient";
        String name2 = "Rennes";
        String name3 = "Saint-Nazaire";
        String name4 = "Montpellier";
        String name5 = "Marseille";

        Map<String, String> map1 = wikipediaAPI.search(name, wikipediaAPI.getEntity(name));
        Map<String, String> map2 = wikipediaAPI.search(name2, wikipediaAPI.getEntity(name2));
        Map<String, String> map3 = wikipediaAPI.search(name3, wikipediaAPI.getEntity(name3));
        Map<String, String> map4 = wikipediaAPI.search(name4, wikipediaAPI.getEntity(name4));
        Map<String, String> map5 = wikipediaAPI.search(name5, wikipediaAPI.getEntity(name5));
        Map<String, List<String>> mapFinal = new LinkedHashMap<>();

        List<Map<String, String>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);

        int i = -1;

        //Résultat final
        for (Map<String, String> map : list) {
            i++;
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                List<String> listFinal;

                Object cle = it.next();
                Object valeur = map.get(cle);

                cle = cle.toString().replace(" ", "");
                if (mapFinal.containsKey(cle)) {
                    listFinal = mapFinal.get(cle);
                    listFinal.add(i, valeur.toString());
                    if (listFinal.size() > list.size()) {
                        for (int j = list.size(); j < listFinal.size(); j++) {
                            listFinal.remove(j);
                        }
                    }
                } else {
                    listFinal = new ArrayList<>();
                    if (i == 0) {
                        listFinal.add(valeur.toString());
                        for (int j = 1; j < list.size(); j++) {
                            listFinal.add(j, "");
                        }
                    } else {
                        for (int j = 0; j < i; j++) {
                            listFinal.add(j, "");
                        }
                        listFinal.add(i, valeur.toString());
                        for (int j = i + 1; j < list.size(); j++) {
                            listFinal.add(j, "");
                        }
                    }
                    mapFinal.put(cle.toString(), listFinal);
                }
            }
        }

        String header = "";
        List<String> datas = new ArrayList<>();
        for (int y = 0; y < list.size(); y++) {
            datas.add(y, "");
        }

        Iterator it = mapFinal.keySet().iterator();
        while (it.hasNext()) {
            //Colonnes
            Object cle = it.next();
            cle = cle.toString().replace("\"", "\'");
            header += "\"" + cle + "\"" + ",";


            //Valeurs
            List<String> valeur = mapFinal.get(cle);

            String s = "";
            for (int x = 0; x < valeur.size(); x++) {
                s = valeur.get(x).replace("\"", "\'");
                datas.set(x, datas.get(x) + "\"" + s + "\"" + ",");
            }
        }

        String data = "";
        for (String str : datas) {
            data += str + "\n";
        }

        header += "\n";


        GestionnaireCSV gestionnaire = new GestionnaireCSV();
        try {
            gestionnaire.addHeader("text", header, data);
            System.out.println("##################################################");
            System.out.println("Génération du csv '" + "text .csv' OK");
        } catch (Exception e) {
            System.out.println("\nErreur lors de la génération du CSV");
        }


    }
}
