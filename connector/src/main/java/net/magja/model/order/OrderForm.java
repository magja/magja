package net.magja.model.order;

import net.magja.model.BaseMagentoModel;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import net.magja.model.address.BasicAddress;
import net.magja.service.order.OrderRemoteService;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Order form to be sent to Magento API which will create a complete order
 * with products. Each product is stored within an {@link OrderFormItem}.
 * @author rudi
 */
@SuppressWarnings("serial")
public class OrderForm extends BaseMagentoModel<Map<String, Object>> {

	private Long customerId;
	private String currencyCode;
	private List<OrderFormItem> items;
	private BasicAddress billingAddress;
	private BasicAddress shippingAddress;

	private String paymentMethod = "banktransfer";
	private String shippingMethod = "flatrate_flatrate";
	private Double shippingAmount;
	private String shippingDescription = "Flat Rate - Fixed";

	public OrderForm() {
		super();
	}

	/**
	 * Used by {@link OrderRemoteService#createEx(OrderForm)} (basic version).
	 * @param customerId
	 * @param currencyCode
	 * @param items
	 */
	public OrderForm(Long customerId, String currencyCode, List<OrderFormItem> items) {
		super();
		this.customerId = customerId;
		this.items = items;
	}

	/**
	 * Used by {@link OrderRemoteService#createEx(OrderForm)} (extended version).
	 * @param customerId
	 * @param items
	 */
	public OrderForm(Long customerId, String currencyCode, List<OrderFormItem> items, BasicAddress billingAddress, BasicAddress shippingAddress) {
		super();
		this.customerId = customerId;
		this.currencyCode = currencyCode;
		this.items = items;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
	}

	@Override
	protected void loadMappings() {
		mapping = new Properties();
		mapping.putAll( ImmutableMap.of("customer_id", (Object)"customerId") );
		mapping.putAll( ImmutableMap.of("currency_code", (Object)"currencyCode") );
		mapping.putAll( ImmutableMap.of("payment_method", (Object)"paymentMethod") );
		mapping.putAll( ImmutableMap.of("shipping_method", (Object)"shippingMethod") );
		mapping.putAll( ImmutableMap.of("shipping_amount", (Object)"shippingAmount") );
		mapping.putAll( ImmutableMap.of("shipping_description", (Object)"shippingDescription") );
		//mapping.putAll( ImmutableMap.of("billingAddress", (Object)"billingAddress"));
		//mapping.putAll( ImmutableMap.of("shippingAddress", (Object)"shippingAddress") );
	}

	/* (non-Javadoc)
	 * @see net.magja.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Map<String, Object> serializeToApi() {
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
		if (billingAddress != null) {
			props.put("billingAddress", billingAddress.serializeToApi());
		}
		if (shippingAddress != null) {
			props.put("shippingAddress", shippingAddress.serializeToApi());
		}
		return props;
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

	/**
	 * @return the billingAddress
	 */
	public BasicAddress getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(BasicAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * @return the shippingAddress
	 */
	public BasicAddress getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(BasicAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the shippingMethod
	 */
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * @param shippingMethod the shippingMethod to set
	 */
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * @return the shippingAmount
	 */
	public Double getShippingAmount() {
		return shippingAmount;
	}

	/**
	 * @param shippingAmount the shippingAmount to set
	 */
	public void setShippingAmount(Double shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	/**
	 * @return the shippingDescription
	 */
	public String getShippingDescription() {
		return shippingDescription;
	}

	/**
	 * @param shippingDescription the shippingDescription to set
	 */
	public void setShippingDescription(String shippingDescription) {
		this.shippingDescription = shippingDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderForm [customerId=" + customerId + ", items=" + items + ", shippingAddress="+ shippingAddress + ", billingAddress="+ billingAddress + "]";
	}

}
