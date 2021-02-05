import org.json.JSONObject;
public class JsonObject extends JSONObject{

    public void addToPath(String path,String key, Object Value){
       String[]pathList = path.split("\\.");
       addValue(pathList,1,key,Value,this);
    }

    private Object addValue(String[] pathList,int  level, String key, Object value,JSONObject json){
        if(pathList.length<=level)
            return json;
        if(level == pathList.length-1 && json.has(pathList[level]) ){
//            json.put(key,value);
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
}
