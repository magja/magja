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

import com.google.code.magja.model.cart.Cart;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

/**
 * @author schneider
 * 
 */
public interface CartRemoteService extends GeneralService<Cart> {

	/**
	 * create new cart for store
	 * 
	 * @param storeid
	 * @return
	 * @throws ServiceException
	 */
	public abstract Cart create(Integer store) throws ServiceException;

	/**
	 * set customer
	 * 
	 * @param cart
	 * @param customer
	 * @return
	 * @throws ServiceException
	 */
	public abstract void setCustomer(Cart cart) throws ServiceException;

	/**
	 * get cart license agreements
	 * 
	 * @param cart
	 * @throws ServiceException
	 */
	public abstract void getLicenseAgreements(Cart cart)
			throws ServiceException;

	/**
	 * retrieve cart totals
	 * 
	 * @param cart
	 * @throws ServiceException
	 */
	public abstract void getTotals(Cart cart) throws ServiceException;

	/**
	 * get cart by quote id
	 * 
	 * @param id
	 * @param storeId
	 * @return
	 * @throws ServiceException
	 */
	public abstract Cart getById(Integer id, Integer storeId)
			throws ServiceException;

	/**
	 * create an order from cart
	 * 
	 * @param cart
	 * @throws ServiceException
	 */
	public abstract void order(Cart cart) throws ServiceException;

	/**
	 * set shipping and billing addresses
	 * 
	 * @param cart
	 * @throws ServiceException
	 */
	public abstract void setAddresses(Cart cart) throws ServiceException;

	/**
	 * add product to shopping cart
	 * 
	 * @param cart
	 * @param product
	 * @param quantity
	 * @throws ServiceException
	 */
	public abstract void addProduct(Cart cart, Product product, double quantity)
			throws ServiceException;

}
