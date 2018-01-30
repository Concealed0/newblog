package com.myblog.aspect;

import com.myblog.common.CustomCacheAnnotation;
import net.sf.ehcache.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author Zephery
 * @since 2018/1/25 12:26
 */
@Aspect
public class CustomCacheAOP {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(CustomCacheAOP.class);
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private Cache ehcache;

    @Pointcut("@annotation(com.myblog.common.CustomCacheAnnotation)")
    public void getCache() {
    }

    /**
     * 在所有标注@getCache的地方切入
     */
    @Around("getCache()")
    public Object beforeExec(ProceedingJoinPoint pjp) {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();
        CustomCacheAnnotation customCacheAnnotation = method.getAnnotation(CustomCacheAnnotation.class);
        String[] value = customCacheAnnotation.value();
        String[] cacheNames = customCacheAnnotation.cacheNames();
        String key = customCacheAnnotation.key();
        String keyGenerator = customCacheAnnotation.keyGenerator();
        String cacheManager = customCacheAnnotation.cacheManager();
        String cacheResolver = customCacheAnnotation.cacheResolver();
        String condition = customCacheAnnotation.condition();
        boolean allEntries = customCacheAnnotation.allEntries();
        String unless = customCacheAnnotation.unless();
        boolean sync = customCacheAnnotation.sync();
        boolean beforeInvocation = customCacheAnnotation.beforeInvocation();
        return "";
    }
}