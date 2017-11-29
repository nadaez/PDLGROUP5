import java.io.IOException;

public interface WikiAPI {

    public String searchEntity(String name) throws IOException;

    public String getEntity(String id) throws IOException;
}
