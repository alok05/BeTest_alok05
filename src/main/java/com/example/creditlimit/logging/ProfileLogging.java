package com.example.creditlimit.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfileLogging {

    private static final String PROFILE_STRING = "{}.{} execution time: {} ms";

    private static final Logger LOGGER = LoggerFactory.getLogger("PerfLog");

    @Pointcut(value = "execution(public * *(..)) ")
    public void anyPublicMethod() {
    }

    @Pointcut("within(@com.example.creditlimit.logging.annotations.Profile *)")
    public void profileBean() {
    }

    @Pointcut(
            "anyPublicMethod() && @annotation(com.example.creditlimit.logging.annotations.Profile)"
    )
    public void profileMethod() {
    }

    @Around("profileBean() || profileMethod()")
    public Object doProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long start = System.currentTimeMillis();

        try {
            return proceedingJoinPoint.proceed();
        } finally {
            Long end = System.currentTimeMillis() - start;

            Object[] params = {
                    proceedingJoinPoint.getTarget().getClass().toString(),
                    proceedingJoinPoint.getSignature().getName(),
                    end
            };

            LOGGER.info(PROFILE_STRING, params);
        }
    }
}
