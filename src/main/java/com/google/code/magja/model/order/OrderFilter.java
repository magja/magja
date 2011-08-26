/**
 * @author andre
 *
 */
package com.google.code.magja.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderFilter implements Serializable {

	private static final long serialVersionUID=-3928174333216515603L;

	private List<OrderFilterItem> items = new ArrayList<OrderFilterItem>();

	public Object serializeToApi() {

		List<Object> props = new LinkedList<Object>();

		for (OrderFilterItem item : items) {

			Map<String, String> params = new HashMap<String, String>();

			params.put(item.getOperator(), item.getValue());

			props.add(item.getProperty());
			props.add(params);
		}

		return props;
	}

	/**
	 * @return the items
	 */
	public List<OrderFilterItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<OrderFilterItem> items) {
		this.items = items;
	}

}
