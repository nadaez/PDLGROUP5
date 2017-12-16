import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
            System.out.println(line);
            content.append(line + "\n");

        }
        bufferedReader.close();

        return new JSONObject(content.toString());
    }
}
