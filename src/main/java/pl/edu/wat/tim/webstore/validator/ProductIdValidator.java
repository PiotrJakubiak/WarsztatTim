package pl.edu.wat.tim.webstore.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wat.tim.webstore.exception.ProductNotFoundException;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.service.ProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ProductIdValidator implements ConstraintValidator<ProductId, Integer>{
	
	@Autowired
	private ProductService productService;

	public void initialize(ProductId constraintAnnotation) {

	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		Product product;
		try {
			product = productService.getProductById(value);

		} catch (ProductNotFoundException e) {
			return true;
		}
		if(product!= null) {
			return false;
		}

		return true;
	}

	
}
