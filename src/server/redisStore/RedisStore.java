package server.redisStore;

import java.util.*;

public class RedisStore implements StoreOptions {

    public static final Map<String, String> string = new HashMap<>();
    public static final Map<String, Map<String, String>> hash = new HashMap<>();


    @Override
    public String set(String key, String value) {
        return string.put(key, value);
    }

    @Override
    public  String get(String key) {
        return string.get(key);
    }

    @Override
    public String del(String key) {
        Map<String, String> result = hash.remove(key);
        if(result == null)
            return "NIL";
        else
            return result.toString();
    }

    @Override
    public int hdel(String key, String field) {
        Map<String, String> map = hash.get(key);
        if(map == null) {
            return 0;
        }
        String removed = map.remove(field);
        if(removed == null) {
            return 0;
        }
        return 1;
    }

    @Override
    public Object hget(String key, String field) {
        Map<String, String> map = hash.get(key);
        if(map == null) {
            return null;
        }
        return map.get(field);
    }

    @Override
    public int hset(String key, String field, String value) {
        Map<String, String> map = hash.computeIfAbsent(key, k -> new HashMap<String, String>());
        int returnValue = 1;
        if(map.containsKey(field)) {
            returnValue = 0;
        }
        map.put(field, value);
        return returnValue;
    }
}

