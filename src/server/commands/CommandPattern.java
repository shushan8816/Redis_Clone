package server.commands;

import server.redisStore.RedisStore;

public enum CommandPattern {
    SET {
        @Override
        public String execute(String[] commands) {
            return RedisStore.string.put(commands[1], commands[2]);
        }
    },
    GET {
        @Override
        public String execute(String[] commands) {
            return RedisStore.string.get(commands[1]);
        }
    },
    DEL {
        @Override
        public String execute(String[] commands) {
            return RedisStore.string.remove(commands[1]);
        }
    };

    public abstract String execute(String[] commands);
}


