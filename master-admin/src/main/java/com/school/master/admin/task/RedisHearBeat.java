package com.school.master.admin.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

//@Component
public class RedisHearBeat {
//    private static final Logger LOGGER = LoggerFactory.getLogger(HearBeatConfig.class);
    @Autowired
    private RedisTemplate redisTemplate;

//    @Scheduled(cron = "0/10 * * * * *")
    public void redisHear() {
        redisTemplate.opsForValue().get("heartbeat");
//        LOGGER.info("redis hear beat");
    }
}
