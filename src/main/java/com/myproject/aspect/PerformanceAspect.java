package com.myproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceAspect {

    Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

    @Pointcut("@annotation(com.myproject.annotation.ExecutionTime)")
    private void  anyExecutionTimeOperation(){}

        @Around("anyExecutionTimeOperation()")
        public Object anyExecutionTimeOperationAdvice(ProceedingJoinPoint proceedingJointPoint) {
            long beforeTime = System.currentTimeMillis();
            Object result = null;

            logger.info("Execution will start");

            try {
                result = proceedingJointPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            long afterTime = System.currentTimeMillis();
            logger.info("Time taken to execute : {} ms : - Method : {} - Paramethers : {}", (afterTime-beforeTime),
                    proceedingJointPoint.getSignature().toString(),proceedingJointPoint.getArgs());

            return result;

        }
}
