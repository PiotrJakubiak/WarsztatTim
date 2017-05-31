package pl.edu.wat.tim.webstore.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @AfterThrowing(pointcut = "Pointcuts.allControllers()", throwing = "exception")
    public void logException(JoinPoint joinPoint, RuntimeException exception)
    {
        logger.error("Wyjątek 'runtime' w controlerze: " + joinPoint.getSignature().toString()
                + " exception: " + exception.getMessage());
    }
}
