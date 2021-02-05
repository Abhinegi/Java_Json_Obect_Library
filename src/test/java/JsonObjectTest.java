import org.json.JSONObject;
import org.junit.Test;

public class JsonObjectTest {
    @Test
    public void jsonTest(){
        JsonObject json = new JsonObject();
        json.addToPath("$.key1","key2","Hello");
        System.out.println(json);
        json.addToPath("$.key1","key3",new JSONObject("{\"boolean\":true}"));
        System.out.println(json.toString(3));
    }
}
