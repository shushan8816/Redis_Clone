package server.redisStore;

import server.Exeptions.BadRequestException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static server.redisStore.RedisStore.hashMap;
import static server.redisStore.RedisStore.stringHashMap;

public class RedisStoreService {
    public static String set(String key, String value) {
        return stringHashMap.put(key, value);
    }

    public static String get(String key) {
        return stringHashMap.get(key);
    }

    public static String getRange(String key, String startIndex, String endIndex) throws BadRequestException {
        int startInd, endInd;
        try {
            startInd = Integer.parseInt(startIndex);
            endInd = Integer.parseInt(endIndex);
        } catch (NumberFormatException error) {
            throw new BadRequestException("Wrong index format.");
        }

        if (startInd > RedisStore.listHashMap.get(key).size() - 1) return "Empty list";
        if (endInd == -1 || endInd > RedisStore.listHashMap.get(key).size() - 1)
            endInd = RedisStore.listHashMap.get(key).size() - 1;

        StringBuilder values = new StringBuilder("\n");
        for (int i = startInd; i <= endInd; i++) {
            values.append(i).append(". ").append(RedisStore.listHashMap.get(key).get(i)).append("\n");
        }
        return values.toString();
    }

    public static String del(String key) {
        Map<String, String> result = hashMap.remove(key);
        if (result == null)
            return "NIL";
        else
            return result.toString();
    }

    public static String hdel(String key) {
        Map<String, String> map = hashMap.get(key);
        if (map == null) {
            return "0";
        }
        String removed = map.remove(key);
        if (removed == null) {
            return "0";
        }
        return "1";
    }

    public static Object hget(String key, String field) {
        Map<String, String> map = hashMap.get(key);
        if (map == null) {
            return null;
        }
        return map.get(field);
    }

    public static int hset(String key, String field) {
        Map<String, String> map = hashMap.computeIfAbsent(key, k -> new HashMap<>());
        int returnValue = 1;
        if (map.containsKey(field)) {
            returnValue = 0;
        }
        map.put(key, field);
        return returnValue;
    }
    public static String leftPush(String key, String[] values) {
        RedisStore.listHashMap.computeIfAbsent(key, k -> new LinkedList<>());
        for (String value : values) {
            RedisStore.listHashMap.get(key).addFirst(value);
        }
        return "size: " + RedisStore.listHashMap.get(key).size();
    }

    public static String rightPush(String key, String[] values) {
        RedisStore.listHashMap.computeIfAbsent(key, k -> new LinkedList<>());
        for (String value : values) {
            RedisStore.listHashMap.get(key).add(value);
        }
        return "size: " + RedisStore.listHashMap.get(key).size();
    }
}
