import org.json.JSONObject;
public class JsonObject extends JSONObject{

    /**
     *
     * @param path
     * @param key
     * @param Value
     */
    public void addToPath(String path,String key, Object Value){
       String[]pathList = path.split("\\.");
       addValue(pathList,1,key,Value,this);
    }

    /**
     * Recursively add search for the path and put the specified value on the key
     * @param pathList
     * @param level
     * @param key
     * @param value
     * @param json
     * @return
     */
    private Object addValue(String[] pathList,int  level, String key, Object value,JSONObject json){
        if(pathList.length<=level)
            return json;
        if(level == pathList.length-1 && json.has(pathList[level]) ){
            Object valueAtPath = json.get(pathList[level]);
            if(valueAtPath instanceof JSONObject)
                json.getJSONObject(pathList[level]).put(key,value);
            else
                json.put(key,value);
        }
        else if(level < pathList.length-1 && json.has(pathList[level]) ){
           Object valueAtPath = json.get(pathList[level]);
           if(valueAtPath instanceof  JSONObject){
               return addValue(pathList,++level,key,value,(JSONObject)((JSONObject) valueAtPath).get(pathList[level]));
           }
           else{
               return addValue(pathList,++level,key,value,new JSONObject());
           }
        }
        else{
            return  json.put(pathList[level],addValue(pathList,++level, key,value,new JSONObject().put(key, value)));
        }
        return  null;
    }

    /**
     *
     * @param json1
     * @param json2
     * @return
     */
    public JSONObject intersection(JSONObject json1, JSONObject json2){
        return getIntersection(json1,json2);
    }

    private JSONObject getIntersection(JSONObject json1, JSONObject json2){
        JSONObject intersectedValues = new JSONObject();
        json1.keySet().forEach(key->{
            if(json2.has(key) && json1.get(key) instanceof JSONObject && json2.get(key) instanceof JSONObject){
                intersectedValues.put(key,getIntersection(json1.getJSONObject(key),json2.getJSONObject(key)));
            }
            else if(json2.has(key) && json1.get(key).getClass()== json2.get(key).getClass()
            && json1.get(key)==json2.get(key)){
                intersectedValues.put(key,json1.get(key));
            }
        });
        return intersectedValues;
    }

}
