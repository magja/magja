/**
 * @author andre
 *
 */
package com.google.code.magja.service.product;

import java.util.Set;

import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductLink;
import com.google.code.magja.model.product.ProductLink.LinkType;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

public interface ProductLinkRemoteService extends GeneralService<ProductLink> {

	/**
	 * List all product links from a specified product
	 *
	 * @param product
	 * @return Set<ProductLink>
	 * @throws ServiceException
	 */
	public abstract Set<ProductLink> list(Product product)
			throws ServiceException;

	/**
	 * List a set of ProductLinks from the specified product with specified link
	 * type (related, up_sell, cross_sell, grouped)
	 *
	 * @param linktype
	 * @param product
	 * @return Set<ProductLink>
	 * @throws ServiceException
	 */
	public abstract Set<ProductLink> list(LinkType linktype, Product product)
			throws ServiceException;

	/**
	 * Assign a ProductLink to a product
	 *
	 * @param product
	 * @param link
	 * @throws ServiceException
	 */
	public abstract void assign(Product product, ProductLink link)
			throws ServiceException;

	/**
	 * Update a product link from a product
	 *
	 * @param product
	 * @param link
	 * @throws ServiceException
	 */
	public abstract void update(Product product, ProductLink link)
			throws ServiceException;

	/**
	 * Remove a product link from a product
	 *
	 * @param product
	 * @param link
	 * @throws ServiceException
	 */
	public abstract void remove(Product product, ProductLink link)
			throws ServiceException;

}
