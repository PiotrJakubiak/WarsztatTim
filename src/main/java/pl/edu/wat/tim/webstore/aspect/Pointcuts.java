package pl.edu.wat.tim.webstore.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut ("execution(* pl.edu.wat.tim.webstore..*Controller.*(..))")
    public void allControllers(){
    }

}
