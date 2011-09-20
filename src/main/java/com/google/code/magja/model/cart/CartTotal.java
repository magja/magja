/**
 * 
 */
package com.google.code.magja.model.cart;

import java.util.Map;

import com.google.code.magja.model.BaseMagentoModel;

/**
 * @author schneider
 * 
 */
public class CartTotal extends BaseMagentoModel {
	
	private static final long serialVersionUID = 6587998968228639825L;

	private String title;

	private Double amount;

	public static CartTotal fromAttributes(Map<String, Object> attrs) {
		CartTotal cartTotal = new CartTotal();
		for (Map.Entry<String, Object> attr : attrs.entrySet()) {
			if (attr.getKey().equals("title")) {
				cartTotal.setTitle((String) attr.getValue());
			} else if (attr.getKey().equals("amount")) {
				Object value = attr.getValue();
				if (value == null) {
					cartTotal.setAmount(0);
				} else if (value instanceof Integer) {
					cartTotal.setAmount((Integer) value);
				} else if (value instanceof Double) {
					cartTotal.setAmount((Double) value);
				}
			}
		}
		return cartTotal;
	}

	@Override
	public Object serializeToApi() {
		return null;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		System.err.println(amount);
	}

	public void setAmount(Integer amount) {
		this.amount = new Double(amount.doubleValue());
	}

	public void setAmount(Double amount) {
		this.amount = (Double) amount;
	}

	@Override
	public String toString() {
		return "CartTotal [" + "title=" + title + ", amount=" + amount + ']';
	}

}
