package com.example.plans.sharedhub.aspects;

import com.example.plans.sharedhub.models.response.BaseResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class TimingAspect {

    @Around("execution(* com.example.plans.*.controllers..*(..))")
    public Object timing(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = Instant.now().toEpochMilli();
        Object result = joinPoint.proceed();
        long elapsedTime = Instant.now().toEpochMilli() - startTime;

        if (result instanceof BaseResponse) {
            ((BaseResponse) result).setCost(elapsedTime);
            ((BaseResponse) result).setTimestamp(String.valueOf(startTime));
        }

        return result;
    }
}
