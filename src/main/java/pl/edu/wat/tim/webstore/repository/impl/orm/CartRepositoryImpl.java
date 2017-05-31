package pl.edu.wat.tim.webstore.repository.impl.orm;

import org.springframework.stereotype.Repository;
import pl.edu.wat.tim.webstore.model.Cart;
import pl.edu.wat.tim.webstore.repository.CartRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepositoryImpl implements CartRepository {
	
	private Map<String, Cart> listOfCarts;
	

	public CartRepositoryImpl() {
		listOfCarts = new HashMap<String,Cart>();
		
	}
	
	
	public Cart create(Cart cart) {
		if(listOfCarts.keySet().contains(cart.getCartId())) {
			throw new IllegalArgumentException(String.format("Nie mona utworzy koszyka. Koszyk o wskazanym  id (%) ju� istnieje.",cart.getCartId()));
		}

		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}
	
	
	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	public void update(String cartId, Cart cart) {
		if(!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Nie mona zaktualizowa koszyka. Koszyk o wskazanym id (%) nie istnieje.",cartId));
		}

		listOfCarts.put(cartId, cart);
	}

	
	public void delete(String cartId) {
		if(!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Nie mona usun koszyka. Koszyk o wskazanym id (%) nie istnieje.",cartId));
		}

		listOfCarts.remove(cartId);
	}

}
