package pl.edu.wat.tim.webstore.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "pl.edu.wat.tim.webstore")
public class AspectConfiguration {
}
