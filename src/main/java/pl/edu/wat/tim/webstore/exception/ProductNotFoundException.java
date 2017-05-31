package pl.edu.wat.tim.webstore.exception;

public class ProductNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -694354952032299587L;
	
	private int productId;

	public ProductNotFoundException(int productId) {
		this.productId = productId;
	}
	
	public int getProductId() {
		return productId;
	}

}
