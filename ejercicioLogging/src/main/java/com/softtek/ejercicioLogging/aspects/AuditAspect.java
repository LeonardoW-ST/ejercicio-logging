package com.softtek.ejercicioLogging.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AuditAspect {

    private static final Logger audit = LoggerFactory.getLogger("AUDIT");

    private static final String SERVICES_POINTCUT =
            "execution(* com.softtek.ejercicioLogging..services..*(..))";

    @Before(SERVICES_POINTCUT)
    public void logBefore(JoinPoint jp) {
        audit.info("ENTRADA -> {}.{}()",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    @AfterReturning(SERVICES_POINTCUT)
    public void logAfter(JoinPoint jp) {
        audit.info("SALIDA  <- {}.{}()",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    @AfterThrowing(pointcut = SERVICES_POINTCUT, throwing = "ex")
    public void logException(JoinPoint jp, Throwable ex) {
        audit.warn("EXCEPCIÓN en {}.{}() -> {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                ex.getClass().getSimpleName());
    }
}
