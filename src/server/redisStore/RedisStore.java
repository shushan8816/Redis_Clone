package server.redisStore;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RedisStore {

    public static ConcurrentHashMap<String, String> stringHashMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, LinkedList<String>> listHashMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, HashMap<String, String>> hashMap = new ConcurrentHashMap<>();
}

