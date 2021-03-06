package pl.edu.wat.tim.webstore.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut ("execution(* pl.edu.wat.tim.webstore..*Controller.*(..))")
    public void allControllers(){
    }

    @Pointcut ("execution(* pl.edu.wat.tim.webstore.jms.*.*(..))")
    public void jmsPackage(){
    }

    @Pointcut ("execution(* pl.edu.wat.tim.webstore.jms.ResponseReceiver.*(..))")
    public void jmsResponseReceiver(){
    }

    @Pointcut ("execution(* pl.edu.wat.tim.webstore.jms.OrderReceiver.*(..))")
    public void jmsOrderReceivers(){
    }

    @Pointcut ("execution(* pl.edu.wat.tim.webstore.jms.ResponseSender.*(..))")
    public void jmsResponseSender(){
    }

    @Pointcut ("execution(void pl.edu.wat.tim.webstore.jms.JmsRunner.runMessages())")
    public void jmsRunner(){
    }

}
