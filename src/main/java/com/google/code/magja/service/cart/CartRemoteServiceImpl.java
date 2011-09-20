/*
 * Copyright 2011 Panticz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.code.magja.service.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.cart.Cart;
import com.google.code.magja.model.cart.CartAddress.Type;
import com.google.code.magja.model.cart.CartLicense;
import com.google.code.magja.model.cart.CartTotal;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;

/**
 * @author schneider
 * 
 */
public class CartRemoteServiceImpl extends GeneralServiceImpl<Cart> implements
		CartRemoteService {

	private static final long serialVersionUID = -7418788716423153907L;

	@Override
	public Cart create(Integer storeId) throws ServiceException {
		try {
			Integer id = (Integer) soapClient.call(
					ResourcePath.ShoppingCartCreate, storeId);
			Cart cart = new Cart();
			cart.setId(id);
			cart.setStoreId(storeId);
			return cart;
		} catch (AxisFault e) {
			if (debug) {
				e.printStackTrace();
			}
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void setCustomer(Cart cart) throws ServiceException {
		try {
			Map<String, Object> callParams = new HashMap<String, Object>();
			Map<String, Object> customerProps = new HashMap<String, Object>();

			Integer customerId = cart.getCustomer().getId();

			// select mode depeding on existence of customer
			if (customerId != null) {
				customerProps.put("mode", "customer");
				customerProps.put("customer_id", customerId);
			} else {
				// TODO: mode=guest, register
				throw new ServiceException(
						"Customer must exist; modes [guest|register] not supported yet");
			}

			callParams.put("quoteId", cart.getId());
			callParams.put("customer", customerProps);
			callParams.put("storeId", cart.getStoreId());

			Boolean success = (Boolean) soapClient.call(
					ResourcePath.ShoppingCartCustomerSet, callParams);
			if (!success) {
				throw new ServiceException("Could not set customer");
			}

		} catch (AxisFault e) {
			if (debug) {
				e.printStackTrace();
			}
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void getLicenseAgreements(Cart cart) throws ServiceException {
		List<Object> result = null;

		try {
			Map<String, Object> callParams = new HashMap<String, Object>();
			callParams.put("quoteId", cart.getId());
			callParams.put("storeId", cart.getStoreId());
			result = (LinkedList<Object>) soapClient.call(
					ResourcePath.ShoppingCartLicenseAgreement, callParams);
		} catch (AxisFault e) {
			if (debug) {
				e.printStackTrace();
			}
			throw new ServiceException(e.getMessage());
		}

		if (result != null) {
			cart.setLicenseAgreements(buildLicenseAgreements(result));
		}
	}

	@Override
	public void getTotals(Cart cart) throws ServiceException {
		List<Object> result = null;

		try {
			Map<String, Object> callParams = new HashMap<String, Object>();
			callParams.put("quoteId", cart.getId());
			callParams.put("storeId", cart.getStoreId());
			result = (LinkedList<Object>) soapClient.call(
					ResourcePath.ShoppingCartTotals, callParams);
		} catch (AxisFault e) {
			if (debug) {
				e.printStackTrace();
			}
			throw new ServiceException(e.getMessage());
		}

		if (result != null) {
			cart.setTotals(buildTotals(result));
		}
	}

	@Override
	public Cart getById(Integer id, Integer storeId) throws ServiceException {
		Map<String, Object> result = null;

		try {
			Map<String, Object> callParams = new HashMap<String, Object>();
			callParams.put("quoteId", id);
			callParams.put("storeId", storeId.toString());
			result = (Map<String, Object>) soapClient.call(
					ResourcePath.ShoppingCartInfo, callParams);
		} catch (AxisFault e) {
			if (debug) {
				e.printStackTrace();
			}
			throw new ServiceException(e.getMessage());
		}
		if (result != null) {
			return Cart.fromAttributes(result);
		}

		return null;
	}

	@Override
	public void order(Cart cart) throws ServiceException {
		try {
			Map<String, Object> callParams = new HashMap<String, Object>();
			callParams.put("quoteId", cart.getId());
			callParams.put("storeId", cart.getStoreId());

			Boolean success = (Boolean) soapClient.call(
					ResourcePath.ShoppingCartOrder, callParams);
			// FIXME: getOrder...
			if (!success) {
				throw new ServiceException("Could not create order from cart");
			}
		} catch (AxisFault e) {
			if (debug) {
				e.printStackTrace();
			}
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void setAddresses(Cart cart) throws ServiceException {
		try {
			List<Object> list = new LinkedList<Object>();
			cart.getShippingAddress().setType(Type.Shipping);
			cart.getBillingAddress().setType(Type.Billing);
			list.add(cart.getShippingAddress().serializeToApi());
			list.add(cart.getBillingAddress().serializeToApi());

			List<Object> params = new LinkedList<Object>();
			params.add(cart.getId());
			params.add(list);
			params.add(cart.getStoreId());

			Boolean success = (Boolean) soapClient.call(
					ResourcePath.ShoppingCartCustomerAddresses, params);
			if (!success) {
				throw new ServiceException(
						"Could not set cart address information");
			}
		} catch (AxisFault e) {
			if (debug) {
				e.printStackTrace();
			}
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void addProduct(Cart cart, Product product, double quantity)
			throws ServiceException {
		// FIXME: product options
		try {
			List<Object> params = new LinkedList<Object>();

			params.add(cart.getId());

			Map<String, Object> props = new HashMap<String, Object>();
			props.put("product_id", product.getId());
			props.put("qty", quantity);
			params.add(props);

			params.add(cart.getStoreId());

			Boolean success = (Boolean) soapClient.call(
					ResourcePath.ShoppingCartProductAdd, params);
			if (!success) {
				throw new ServiceException("Could not add product");
			}
		} catch (AxisFault e) {
			if (debug) {
				e.printStackTrace();
			}
			throw new ServiceException(e.getMessage());
		}
	}

	private List<CartLicense> buildLicenseAgreements(List<Object> result) {
		List<CartLicense> licenses = new ArrayList<CartLicense>();

		for (Object o : result) {
			Map<String, Object> licenseAttributes = (Map<String, Object>) o;
			licenses.add(CartLicense.fromAttributes(licenseAttributes));
		}

		return licenses;
	}

	private List<CartTotal> buildTotals(List<Object> result) {
		List<CartTotal> totals = new ArrayList<CartTotal>();

		for (Object o : result) {
			Map<String, Object> totalAttributes = (Map<String, Object>) o;

			totals.add(CartTotal.fromAttributes(totalAttributes));
		}

		return totals;
	}

}
