package pl.edu.wat.tim.webstore.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ProductIdValidator.class)
@Documented
public @interface ProductId {
	   String message() default "Produkt o podanym ID już istnieje.";

	    Class<?>[] groups() default {};
	    
	    public abstract Class<? extends Payload>[] payload() default {};
}
