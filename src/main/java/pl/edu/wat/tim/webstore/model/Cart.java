package pl.edu.wat.tim.webstore.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	private String cartId;
	private Map<Integer,CartItem> cartItems;
	private BigDecimal grandTotal;
	
	public Cart() {
		cartItems = new HashMap<Integer, CartItem>();
		grandTotal = new BigDecimal(0);
	}
	
	public Cart(String cartId) {
		this();
		this.cartId = cartId;
	}
	
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<Integer, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<Integer, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	
	public void addCartItem(CartItem item) {
		int productId = item.getProduct().getProductId();
		
		if(cartItems.containsKey(productId)) {
			CartItem existingCartItem = cartItems.get(productId);
			existingCartItem.setQuantity(existingCartItem.getQuantity()+ item.getQuantity());
			cartItems.put(productId, existingCartItem);
		} else {
			cartItems.put(productId, item);
		}
		updateGrandTotal();
	}
	
	public void removeCartItem(CartItem item) {
		int productId = item.getProduct().getProductId();
		cartItems.remove(productId);
		updateGrandTotal();
	}
	
	public void updateGrandTotal() {
		grandTotal= new BigDecimal(0);
		for(CartItem item : cartItems.values()){
			grandTotal = grandTotal.add(item.getTotalPrice());
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 71;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		return true;
	}
}
