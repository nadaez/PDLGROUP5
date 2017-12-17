import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class WikidataAPI implements WikiAPI {

    /**
     * Permet de chercher toutes les entités correspondant au nom rentré en paramètre
     *
     * @param name
     * @return JSONObect avec les entités trouvées.
     * @throws IOException
     */
    @Override
    public JSONObject searchEntity(String name) throws IOException, JSONException {
        String url = "https://www.wikidata.org/w/api.php?action=wbsearchentities&search=" + name + "&language=fr&format=json";
        //System.out.println("Test : " + search(url));
        return search(url);
    }

    /**
     * Permet de chercher pour un id, l'entité correspondante
     *
     * @param id
     * @return JSONObject avec toutes les informations de l'entité
     * @throws IOException
     */
    @Override
    public JSONObject getEntity(String id) throws IOException, JSONException {
        String url = "https://www.wikidata.org/w/api.php?action=wbgetclaims&entity=" + id + "&format=json";
        return search(url);
    }

    public JSONObject search(String str) throws IOException, JSONException {
        StringBuilder content = new StringBuilder();

        String url = str;
        URL search = new URL(url);
        URLConnection searchConnection = search.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(searchConnection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            //System.out.println(line);
            content.append(line + "\n");

        }
        bufferedReader.close();

        return new JSONObject(content.toString());
    }

    public String getDescription(String pCode) throws IOException, JSONException {
        String url = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=" + pCode + "&props=labels&languages=en&format=json";
        String res = search(url).toString();
        int begin = res.indexOf("\"value") + 9;
        int end = res.indexOf("\"}");
        return res.substring(begin, end);
    }


    public Map<String, List<String>> getTableProp(JSONObject object) throws JSONException {
        Map<String, List<String>> tableProp = new HashMap<String, List<String>>();
        String str = object.toString();
        List<String> listPString = new ArrayList<>();
        List<String> listQString = new ArrayList<>();
        int indexQ = str.indexOf("\"id") + 6;
        int endQ = str.indexOf("\"", indexQ);
        int indexP = str.indexOf("property") + 11;
        int endP = str.indexOf("\"", indexP);
        listPString.add(str.substring(indexP, endP));
        listQString.add(str.substring(indexQ, endQ));

        while (indexP >= 0) {
            indexP = str.indexOf("property", indexP + 1);
            endP = str.indexOf("\"", indexP + 11);
            String s = str.substring(indexP + 11, endP);
            if (Character.isUpperCase(s.charAt(0)) && !s.contains("-")) {
                listPString.add(s);
            }

        }
        while (indexQ >= 0) {
            indexQ = str.indexOf("id", indexQ + 1);
            endQ = str.indexOf("\"", indexQ + 6);
            String s = str.substring(indexQ + 5, endQ);
            if (Character.isUpperCase(s.charAt(0)) && !s.contains("-")) {
                listQString.add(s);
            }
        }

        /*for(int i = 0; i < tableProp.size(); i ++){
            System.out.println(tableProp.get(i));
        }*/
        return tableProp;
    }

    public Set<String> getProperties(Set<String> pCodes) throws IOException, JSONException {
        Set<String> valeurs = new HashSet<String>();
        for (String code : pCodes) {
            valeurs.add(getDescription(code));
        }
        return valeurs;
    }

}
