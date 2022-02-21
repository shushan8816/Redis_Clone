package server.commands;

import server.Exeptions.BadRequestException;
import server.Utils.CommandUtil;
import server.redisStore.RedisStoreService;

public enum CommandPattern {

    SET {
        @Override
        public String execute(String[] commands) throws BadRequestException {
            if (commands.length != 2) throw new BadRequestException("Wrong SET command.");
            return RedisStoreService.set(commands[1], commands[2]);
        }
    },
    HSET {
        @Override
        public String execute(String[] commands) throws BadRequestException {
            if (commands.length < 3) throw new BadRequestException("Wrong HSET command.");
            return String.valueOf(RedisStoreService.hset(commands[1], commands[2]));
        }
    },
    GET {
        @Override
        public String execute(String[] commands) throws BadRequestException {
            if (commands.length != 1) throw new BadRequestException("Wrong GET command.");
            return RedisStoreService.get(commands[1]);
        }
    },
    HGET {
        @Override
        public String execute(String[] commands) throws BadRequestException {
            if (commands.length != 2) throw new BadRequestException("Wrong HGET command.");
            return String.valueOf(RedisStoreService.hget(commands[1], commands[2]));
        }
    },
    RPUSH {
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length < 2) throw new BadRequestException("Wrong RPUSH command.");
            return RedisStoreService.rightPush(keyValues[0], CommandUtil.removeFirstElementOfStringArray(keyValues));
        }
    },
    LPUSH {
        @Override
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length < 2) throw new BadRequestException("Wrong LPUSH command.");
            return RedisStoreService.leftPush(keyValues[0], CommandUtil.removeFirstElementOfStringArray(keyValues));
        }
    },
    LRANGE {
        @Override
        public String execute(String[] commands) throws BadRequestException {
            if (commands.length != 3) throw new BadRequestException("Wrong LRANGE command.");
            return RedisStoreService.getRange(commands[1], commands[2],commands[3]);
        }
    },
    DEL {
        @Override
        public String execute(String[] commands) {
            return RedisStoreService.del(commands[1]);
        }
    },
    HDEL {
        @Override
        public String execute(String[] commands) {
            return RedisStoreService.hdel(commands[1]);
        }
    };

    public abstract String execute(String[] commands) throws BadRequestException;
}


