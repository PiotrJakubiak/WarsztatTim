package pl.edu.wat.tim.webstore.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Before("execution(* pl.edu.wat.tim.webstore..*.*(..))")
    void entering(JoinPoint joinPoint){
        logger.warn("Entering: " + joinPoint.getStaticPart().getSignature().toString());
    }

}
