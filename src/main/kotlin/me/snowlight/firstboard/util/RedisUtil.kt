package me.snowlight.firstboard.util

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisUtil(
    private val redisTemplate: RedisTemplate<String, Any>,
) {
    fun setDate(key: String, value: Any) {
        redisTemplate.opsForValue().set(key, value.toString())
    }

    fun getDate(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }

    fun increment(key: String) {
        redisTemplate.opsForValue().increment(key, 1L)
    }

    fun getCount(key: String): Long? {
        return redisTemplate.opsForValue().get(key)?.toString()?.toLong()
    }

    fun getLikeCountKey(postId: Long): String {
        return "like:${postId}"
    }
}
