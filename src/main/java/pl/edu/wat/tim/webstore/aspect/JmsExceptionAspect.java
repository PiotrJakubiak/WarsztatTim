package pl.edu.wat.tim.webstore.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JmsExceptionAspect {

    private Logger logger = Logger.getLogger(JmsLoggingAspect.class.getName());

    @AfterThrowing(pointcut = "Pointcuts.allControllers()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception)
    {
        logger.error("Wyjątek w controlerze: " + joinPoint.getSignature().toString()
                + " : " + exception.getCause());
    }
}
