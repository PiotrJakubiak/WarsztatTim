package pl.edu.wat.tim.webstore.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;


//ASPECT = POINTCUT + ADVICE
/*
 POINTCUT - Where the Aspect is applied
 ADVICE - What code is executed
*/

//Aspekt który wywołuje logger
@Aspect
//Jest SpringBeanem
@Component
public class LoggingAspect {

     private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

     boolean enteringCalled = true;


    //Advice, oznaczamy ją adnotacją np. @Before (w nim jest (Pointcut))
    //@Before - wykonywane przed kodem metody zdef w execution
    //Pointcut - definicja kiedy ma być wywoływany. Tutaj jest to 'method execution pointcut'. Składa się ze słowa
    //kluczowego 'execution' oraz definicji metody, która ma wywołać Advice - tutaj jest to metoda o
    //nagłówku/prototypie 'void doSomething()'
    //typ zwracany - nazwa metody - paramety
    //mozemy to zastąpić przez 'wildcards' -> * (dowolny)
    // * * (..) - to dopasuje kazda metode we wszystkich spring beanach

    @Before(
        "execution(* receiveMessage(*))"
    )
    void entering(JoinPoint joinPoint){
        logger.info("Message was send: " + joinPoint.getStaticPart().getSignature().toString());
    }

    //Aby dowiedziec sie jaka metoda została wywołana można użyć JoinPointów.
    //Zwieraja one informacje o metodzie, która wykonuje Advice
}
