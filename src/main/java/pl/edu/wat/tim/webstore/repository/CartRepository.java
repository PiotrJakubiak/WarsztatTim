package pl.edu.wat.tim.webstore.repository;


import pl.edu.wat.tim.webstore.model.Cart;

public interface CartRepository {

	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);

}
