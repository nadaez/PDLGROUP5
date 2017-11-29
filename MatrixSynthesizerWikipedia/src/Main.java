import org.json.JSONException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        for (String str : lObj){
            System.out.println(wikidataAPI.getEntity(str));
        }

        System.out.println(wikidataAPI.getProperty("Q90"));

    }
}
