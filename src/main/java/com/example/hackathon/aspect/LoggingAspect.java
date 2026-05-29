package com.example.hackathon.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(* com.example.hackathon.service.impl.MovieServiceImpl.create(..)) ||"+
    "execution(* com.example.hackathon.service.impl.MovieServiceImpl.update(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Method Name:{}",joinPoint.getSignature().getName());
    }
}
