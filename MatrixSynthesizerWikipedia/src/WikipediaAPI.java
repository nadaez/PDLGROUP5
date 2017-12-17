import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WikipediaAPI implements WikiAPI {
    @Override
    public JSONObject searchEntity(String name)  throws IOException , JSONException {
        String url = "https://en.wikipedia.org/w/api.php?action=query&list=prefixsearch&pssearch="+name+"&format=json";

        return search(url);
    }

    @Override
    public JSONObject getEntity(String id)  throws IOException , JSONException {
        String url = "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&rvprop=content&rvsection=0&titles="+id+"&format=json";
        return search(url);
    }

    public JSONObject search(String str)  throws IOException , JSONException {
        StringBuilder content = new StringBuilder();

        String url = str;
        URL search = new URL(url);
        URLConnection searchConnection = search.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(searchConnection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            //System.out.println(line);
            content.append(line + "\n");

        }
        bufferedReader.close();

        return new JSONObject(content.toString());
    }

    public Map<String,String> search(String name , JSONObject obj){
        String str = obj.toString();
        int indexDébut = str.indexOf("box");
        int indexFin;
        int indexMilieu = 0 ;
        String scle,svaleur = "";
        Map<String,String> map = new LinkedHashMap<>();
        name = name.toString().replace("_"," ");
        System.out.println("'''" + name + "'''");
        System.out.println("Fin "+ str.indexOf("'''" + name));

        while(indexDébut != -1 && indexDébut<str.indexOf("'''" + name) && indexMilieu<str.indexOf("'''" + name)) {
            indexDébut = str.indexOf("|" , indexDébut);
            indexMilieu = str.indexOf("=",indexDébut);
            indexFin = str.indexOf("\\n",indexDébut);
            System.out.println("*************************");
            scle = str.substring(indexDébut+1,indexMilieu);
            svaleur = str.substring(indexMilieu+1,indexFin);
            System.out.println("clé : " + scle);
            System.out.println("valeur : " + svaleur);
            map.put(scle,svaleur);
            indexDébut = indexFin ;
            System.out.println("index : " + indexDébut);
            indexDébut = str.indexOf("|" , indexDébut);
            indexMilieu = str.indexOf("=",indexDébut);
            System.out.println("index2 : " + indexDébut);
        }

        return map;

    }
}
