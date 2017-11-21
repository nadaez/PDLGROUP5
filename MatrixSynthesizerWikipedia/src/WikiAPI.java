import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

public interface WikiAPI {

    public JSONObject searchEntity(String name) throws IOException;

    public JSONObject getEntity(String id) throws IOException;
}
