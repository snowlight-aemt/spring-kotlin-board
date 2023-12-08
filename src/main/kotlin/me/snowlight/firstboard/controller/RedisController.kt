package me.snowlight.firstboard.controller

import me.snowlight.firstboard.util.RedisUtil
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class RedisController(
    private val redisUtil: RedisUtil,
) {
    @GetMapping("/redis")
    fun redis(): Long {
        redisUtil.increment("redis")
        return redisUtil.getCount("redis") ?: 0L
    }
}
