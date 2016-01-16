/**
 * @author andre
 *
 */
package com.google.code.magja.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter implements Serializable {

	private static final long serialVersionUID=-3928174333216515603L;

	private List<FilterItem> items = new ArrayList<FilterItem>();
	
	public Object serializeToApi() {

		// Map each property to it's parameter map
		Map<String, Map<String, String>> propertyMaps = new HashMap<String, Map<String, String>>();

		for (FilterItem item : getItems()) {

			String property = item.getProperty();

			// If we have this property already, add to the existing paramMap
			if (propertyMaps.get(property) != null) {
				propertyMaps.get(property).put(item.getOperator(), item.getValue());
			} else { // new property, new paramMap
				Map<String, String> params = new HashMap<String, String>();
				params.put(item.getOperator(), item.getValue());
				propertyMaps.put(property, params);
			}
		}
		return propertyMaps;
	}

	/**
	 * @return the items
	 */
	public List<FilterItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<FilterItem> items) {
		this.items = items;
	}

}
