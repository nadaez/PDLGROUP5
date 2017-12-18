import org.json.JSONException;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, JSONException {

        System.out.println("Pour lancer une recherche vous devez saisir des informations dans le fichier : fichierConfig.txt");
        System.out.println("Afin que les recherches puissent s'effectuer vous devez respecter la syntaxe de Wikipédia. ");
        System.out.println("Exemple : si vous recherchez 'Pomme' il faudra marquer 'Apple' sinon il n'y aura aucune page correspondante. ");

        /////////******* RECUPERATION DES DONNEES SUR LE FICHIER ********//////////
        List<String> donneesWikipedia = donneesFichierWikiPedia();
        //List<String> donneesWikidata = donneesFichierWikiData();

        /////////******** PARTIE : WIKIDATA ********////////
        WikidataAPI wikidataAPI = new WikidataAPI();

        //Test des méthodes
        //System.out.println(wikidataAPI.searchEntity("Paris"));
        //System.out.println(wikidataAPI.getEntity("Q90"));
        //System.out.println("GetPorperty : "+ wikidataAPI.getProperty("Q90"));

        //////********* PARTIE : WIKIPEDIA ********//////
        WikipediaAPI wikipediaAPI = new WikipediaAPI();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, List<String>> mapFinal = new LinkedHashMap<>();
        GestionnaireCSV gestionnaire = new GestionnaireCSV();

        for(String name : donneesWikipedia){
            Map<String, String> map = wikipediaAPI.search(name, wikipediaAPI.getEntity(name));
            list.add(map);
        }

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

        if(data.length() != 0 ) {
            try {
                gestionnaire.addHeader("PCM", header, data);
                System.out.println("##################################################");
                System.out.println("Génération du csv '" + "PCM .csv' OK");
            } catch (Exception e) {
                System.out.println("\nErreur lors de la génération du CSV");
            }
        }


    }

    public static List<String> donneesFichierWikiData(){
        String[] temp;
        String delimiter = ",";
        List<String> lObj = new ArrayList<String>();

        try{
            InputStream flux = new FileInputStream("MatrixSynthesizerWikipedia/File/FichierConfig.txt");
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                System.out.println(ligne);
                temp = ligne.split(delimiter);
                for(int i =0; i < temp.length ; i++){
                    int p1 = temp[i].indexOf('(');
                    int p2 = temp[i].indexOf(')');
                    lObj.add(temp[i].substring(p1+1, p2));
                }
            }
            buff.close();
        }	catch (Exception e) {
            System.out.println(e.toString());
        }

        return lObj;
    }

    public static List<String> donneesFichierWikiPedia(){
        String[] temp;
        String delimiter = ",";
        List<String> lObj = new ArrayList<String>();

        try{
            InputStream flux = new FileInputStream("MatrixSynthesizerWikipedia/File/FichierConfig.txt");
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                System.out.println("Votre recherche : ");
                temp = ligne.split(delimiter);
                for(int i =0; i < temp.length ; i++){
                    lObj.add(temp[i]);
                    System.out.println(temp[i]);
                }
            }
            buff.close();
        }	catch (Exception e) {
            System.out.println(e.toString());
        }

        return lObj;
    }
}
