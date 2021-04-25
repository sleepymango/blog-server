package com.sleepymango.blog.utils;

import com.sleepymango.blog.entity.Article;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description RedisDelayUtil
 * @Author sleepymango
 * @Date 2021/4/21 16:44
 **/
@Component
public class RedisDelayUtil {
    private static final String ARTICLE_QUEUE_KEY = "article:queue";

    private final RedisTemplate<String,Long> redisTemplate;

    public RedisDelayUtil(RedisTemplate<String,Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 存放文章id信息
     *
     * @param article
     * @return
     */
    public boolean push(Article article) {
        long publish = article.getPublish().getTime();
        return redisTemplate.opsForZSet().add(ARTICLE_QUEUE_KEY, article.getId(), publish);
    }

    public boolean remove(Long articleId) {
        Long remove = redisTemplate.opsForZSet().remove(ARTICLE_QUEUE_KEY, articleId);
        return remove > 0;
    }

    /**
     * 获取
     * @return
     */
    public Set<Long> pull(){
        // redis存入Long 取出来会自动转成Integer
        // 取出过期时间的文章id
        return Objects.requireNonNull(redisTemplate.opsForZSet().rangeByScore(ARTICLE_QUEUE_KEY, 0, System.currentTimeMillis()))
                .stream().peek(d->Long.parseLong(d.toString())).collect(Collectors.toSet());
    }

}
