import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

public class WikidataAPI implements WikiAPI{

    /**
     * Permet de chercher toutes les entités correspondant au nom rentré en paramètre
     * @param name
     * @return JSONObect avec les entités trouvées.
     * @throws IOException
     */
    @Override
    public JSONObject searchEntity(String name) throws IOException {
        String url = "https://www.wikidata.org/w/api.php?action=wbsearchentities&search="+name+"&language=fr&format=json";
        ///return search(url);
        return null;
    }

    /**
     * Permet de chercher pour un id, l'entité correspondante
     * @param id
     * @return JSONObject avec toutes les informations de l'entité
     * @throws IOException
     */
    @Override
    public JSONObject getEntity(String id) throws IOException {
        String url = "https://www.wikidata.org/w/api.php?action=wbgetclaims&entity="+id+"&format=json";
        //return search(url);
        return null;
    }

    public String search(String str) throws IOException {
        StringBuilder content = new StringBuilder();

        String url = str;
        URL search = new URL(url);
        URLConnection searchConnection = search.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(searchConnection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            System.out.println(line);
            content.append(line + "\n");

        }
        bufferedReader.close();

        return content.toString();
    }

    public String getProperty(String pCode) throws IOException {
        String url ="https://www.wikidata.org/w/api.php?action=wbgetentities&ids=" + pCode + "&props=labels&languages=en&format=json";
        String res = search(url);
        int begin = res.indexOf("\"value")+9;
        int end = res.indexOf("\"}");
        return res.substring(begin, end);
    }

    public Set<String> getProperties(Set<String> pCodes) throws IOException {
        Set<String> valeurs = new HashSet<String>();
        for(String code : pCodes){
            valeurs.add(getProperty(code));
        }
        return valeurs;
    }


}
