import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class JsonObjectTest {
    @Test
    public void jsonTest(){
        JsonObject json = new JsonObject();
        json.addToPath("$.key1","key2","Hello");
        System.out.println(json);
        json.addToPath("$.key1","key3",new JSONObject("{\"boolean\":true}"));
        System.out.println(json.toString(3));
    }

    @Test
    @DisplayName("Intersection Test")
    public void testIntersection(){
        JSONObject json1 = new JSONObject("{\"key1\":{\"inside\":\"Value\",\"insideKey2\":true},\"key2\":false}");
        JSONObject json2 = new JSONObject("{\"key1\":{\"insideKey2\":true}}");
        JsonObject json=new JsonObject();
        System.out.println(json.intersection(json1,json2));
        // Checking for inner key comparison
         json1 = new JSONObject("{\"key1\":{\"inside\":\"Value\",\"insideKey2\":true},\"key2\":false}");
         json2 = new JSONObject("{\"key1\":{\"insideKey2\":false}}");
        System.out.println(json.intersection(json1,json2));
        //checking for json array
        json1 = new JSONObject("{\"key1\":[\"a\",\"b\"]}");
        json2 = new JSONObject("{\"key1\":[\"a\",\"b\"]}");
        System.out.println(json.intersection(json1,json2));
    }
}
