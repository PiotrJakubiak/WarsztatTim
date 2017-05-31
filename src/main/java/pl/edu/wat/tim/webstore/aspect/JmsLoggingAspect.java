package pl.edu.wat.tim.webstore.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.model.Product;


@Aspect
@Component
@SuppressWarnings("unchecked")
public class JmsLoggingAspect {

    private Logger logger = Logger.getLogger(JmsLoggingAspect.class.getName());

    @Before("Pointcuts.jmsPackage()")
    void entering(JoinPoint joinPoint){
        logger.info("Entering: " + joinPoint.getStaticPart().getSignature().toString());
    }

    @After("Pointcuts.jmsOrderReceivers()")
    void afterOrderReceive(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Message<Product> message = (Message<Product>)args[0];
        logger.info("OrderReceived : " + message.getPayload());
    }

    @After("Pointcuts.jmsResponseReceiver()")
    void afterResponseReceive(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Message<Product> message = (Message<Product>)args[0];
        logger.info("ResponseReceived : " + message.getPayload());
    }

    @After("Pointcuts.jmsResponseSender()")
    void afterSender(JoinPoint joinPoint){
        logger.info("ResponseSender : " + joinPoint.getSignature().toString());
    }
}
