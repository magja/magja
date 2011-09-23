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
		
		// Map each property to it's parameter map
		Map<String, Map<String, String>> propertyMaps = new HashMap<String, Map<String, String>>();
		
		for (OrderFilterItem item : items) {
			
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

		// output in the required format, a list of maps [property -> [paramMap]]
		List<Map<String, Map<String, String>>> result = 
				new LinkedList<Map<String,Map<String,String>>>();
		
		Map<String, Map<String, String>> newMap;
		
		for (String property : propertyMaps.keySet()) {
			newMap = new HashMap<String, Map<String,String>>();
			newMap.put(property, propertyMaps.get(property));
			result.add(newMap);
		}
		
		return result;
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
