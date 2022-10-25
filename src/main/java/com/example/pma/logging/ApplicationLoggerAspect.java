package com.example.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.example.pma.controllers..*)")
    public void definePackagePointcuts() {
        // empty method just to name the location specified in the pointcut
    }

    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint jp) {
        logger.debug("*******************************************************");
        logger.debug("************** BEFORE METHOD EXECUTION ****************");
        logger.debug(jp.getSignature().getDeclaringTypeName());
        logger.debug(jp.getSignature().getName());
        logger.debug(Arrays.toString(jp.getArgs()));
        logger.debug("************** END ****************");

        Object o = null;
        try {
            o = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        logger.debug("*******************************************************");
        logger.debug("************** AFTER METHOD EXECUTION ****************");
        logger.debug(jp.getSignature().getDeclaringTypeName());
        logger.debug(jp.getSignature().getName());
        logger.debug(Arrays.toString(jp.getArgs()));
        logger.debug("************** END ****************");

        return o;
    }
}
