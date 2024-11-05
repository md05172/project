package kr.ac.kopo.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Long, Integer> cart = new HashMap<Long, Integer>();

	public Map<Long, Integer> getCart() {
		return cart;
	}

	public void setCart(Long productId, Integer amount) {
		// 이미 카드에 상품이 있다면 수량 증가
		if(cart.containsKey(productId)) 
			cart.put(productId, cart.get(productId) + amount );
		else 
			cart.put(productId, amount);
	}

	public void delete(Long id) {
		cart.remove(id);
	}

}
