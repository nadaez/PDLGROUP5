package Group5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public interface WikiAPI {

    public JSONObject searchEntity(String name) throws IOException , JSONException;

    public JSONObject getEntity(String id)  throws IOException , JSONException;
}
