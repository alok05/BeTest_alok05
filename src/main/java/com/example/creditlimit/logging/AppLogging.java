package com.example.creditlimit.logging;

import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppLogging {

    private static final String AFTER_RETURNING = "exiting method {}()";
    private static final String THROWING = "threw the exception ";
    private static final String METHOD_ENTERING = "Entering : ";
    private static final String METHOD_EXITING = "Exited : ";

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }

    @Pointcut("within(@com.example.creditlimit.logging.annotations.Log *)")
    public void logBean() {
    }

    @AfterThrowing(pointcut = "allMethod() && logBean()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        Pair<String, Object[]> pair = buildParams(joinPoint.getSignature().getName(), exception);
        logger.error(pair.getKey(), pair.getValue());
        Throwable cause = exception.getCause();
        if (cause != null) {
            logger.error("Cause: {}", cause);
        }
    }

    @Before(
            "execution(* com.example.creditlimit.controller.CreditLimitController.*(..))&& args(arg,..)"
                    + " || execution(* com.example.creditlimit.service.CreditLimitServiceImpl.*(..))&& args(arg,..)"
                    + " || execution(* com.example.creditlimit.reader.CSVReader.*(..))&& args(arg,..)"
                    + " || execution(* com.example.creditlimit.reader.PRNReader.*(..))&& args(arg,..)")
    public void logBeforeMethodAccess(JoinPoint joinPoint, Object arg) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info(
                METHOD_ENTERING
                        + joinPoint.getSignature().getDeclaringTypeName()
                        + "->"
                        + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "execution(* com.example.creditlimit.controller.CreditLimitController.*(..))"
                    + " || execution(* com.example.creditlimit.service.CreditLimitServiceImpl.*(..))"
                    + " || execution(* com.example.creditlimit.reader.CSVReader.*(..))"
                    + " || execution(* com.example.creditlimit.reader.PRNReader.*(..))"
    )
    public void logAfterMethodAccess(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info(
                METHOD_EXITING
                        + joinPoint.getSignature().getDeclaringTypeName()
                        + "->"
                        + joinPoint.getSignature().getName());
    }

    private Pair<String, Object[]> buildParams(String signature, Object... params) {
        final Object[] loggingParams = new Object[params.length + 1];
        loggingParams[0] = signature;
        final StringBuilder buffer = new StringBuilder(AFTER_RETURNING);

        if (params.length > 0) {
            buffer.append(THROWING);
            System.arraycopy(params, 0, loggingParams, 1, params.length);
            for (int i = 0; i < params.length; i++) {
                buffer.append(" {} ");
            }
        }

        return Pair.of(buffer.toString(), loggingParams);
    }
}
