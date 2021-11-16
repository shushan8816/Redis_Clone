package server.redisStore;

public interface StoreOptions {

    String set(String key, String value);

    String get(String key);

    String del(String key);

    int hdel(String key, String field);

    Object hget(String key, String field);

    int hset(String key, String field, String value);

}
