import org.json.JSONException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

        //Récupération des données à partir des clés obtenues dans le fichier de configuration
        String dataCsv ="";
        for (String str : lObj){
            //System.out.println(wikidataAPI.getEntity(str));
            dataCsv += str +"\n";
        }

       System.out.println("GetPorperty : "+ wikidataAPI.getProperty("Q90"));
        GestionnaireCSV gestionnaire = new GestionnaireCSV();
        try{
            gestionnaire.addHeader("text", "titre\n", dataCsv);
            System.out.println("##################################################");
            System.out.println("Génération du csv '" + "text .csv' OK");
        } catch (Exception e){
            System.out.println("\nErreur lors de la génération du CSV");
        }
        //WikipediaAPI wikipediaAPI = new WikipediaAPI();

        //System.out.println("WIKIPEDIA !! ");
        //System.out.println(wikipediaAPI.searchEntity("paris"));
        //System.out.println(wikipediaAPI.getEntity("Paris"));

    }
}
