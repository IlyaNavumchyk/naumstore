package com.naumstore.aop;

import com.naumstore.util.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingServiceAspect {

    @Pointcut("within(com.naumstore.service..*)")
    private void aroundServicePointcut() {
    }

    @Around("aroundServicePointcut()")
    public Object logAroundServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodExecutionId = UUIDGenerator.generateUUID();
        Signature signature = joinPoint.getSignature();
        log.info("{} method {} in {} start with args {}",
                methodExecutionId, signature.getName(), signature.getDeclaringType(), joinPoint.getArgs());

        Object proceed;
        try {
            proceed = joinPoint.proceed();
            log.info("{} method {} in {} successful finished",
                    methodExecutionId, signature.getName(), signature.getDeclaringType());
        } catch (Exception e) {
            log.info("{} method {} in {} threw an exception {} with massage {}",
                    methodExecutionId, signature.getName(), signature.getDeclaringType(),
                    e.getClass().getSimpleName(), e.getMessage());
            throw e;
        }

        return proceed;
    }
}
