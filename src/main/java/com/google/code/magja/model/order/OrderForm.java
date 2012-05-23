package com.google.code.magja.model.order;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

/**
 * Order form to be sent to Magento API which will create a complete order
 * with products. Each product is stored within an {@link OrderFormItem}.
 * @author rudi
 */
@SuppressWarnings("serial")
public class OrderForm extends BaseMagentoModel {

	private Long customerId;
	private List<OrderFormItem> items;
	
	public OrderForm() {
		super();
	}
	
	/**
	 * @param customerId
	 * @param items
	 */
	public OrderForm(Long customerId, List<OrderFormItem> items) {
		super();
		this.customerId = customerId;
		this.items = items;
	}
	
	@Override
	protected void loadMappings() {
		mapping = new Properties();
		mapping.putAll( ImmutableMap.of("customer_id", (Object)"customerId") );
	}

	/* (non-Javadoc)
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		Map<String, Object> props = getAllProperties();
		List<Map<String, ?>> itemsApi = Lists.transform(items,	new Function<OrderFormItem, Map<String, ?>>() {
			@Override
			public Map<String, ?> apply(OrderFormItem input) {
				return ImmutableMap.of(
						"product_id", input.getProductId(),
						"qty", input.getQty());
			}
		});
		props.put("items", itemsApi);
		return new Map[] { props };
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderForm [customerId=" + customerId + ", items=" + items + "]";
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the items
	 */
	public List<OrderFormItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<OrderFormItem> items) {
		this.items = items;
	}

}
