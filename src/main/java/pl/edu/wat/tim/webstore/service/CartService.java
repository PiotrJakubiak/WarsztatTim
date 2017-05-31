package pl.edu.wat.tim.webstore.service;


import pl.edu.wat.tim.webstore.model.Cart;

public interface CartService {
	
	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);

}
