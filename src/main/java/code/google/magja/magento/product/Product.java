/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Product API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/catalog_product
 */
package code.google.magja.magento.product;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;

public class Product extends Connection {

	/*
	 * constructor
	 */
	public Product() {
		super();
	}

	/*
	 * get product list
	 */
	@SuppressWarnings("unchecked")
	public String getList() {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client
					.call(ResourcePath.ProductList, "");

			return Utils.dump(productList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Retrieve product informations
	 *
	 * @param sku
	 *            product SKU
	 * @return array
	 */
	public String getInfo(String sku) {
		try {
			Map<String, Object> product = (Map<String, Object>) client.call(
					ResourcePath.ProductInfo, sku);

			return Utils.dump(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Retrieve product informations
	 *
	 * @param sku
	 *            product id
	 * @return array
	 */
	public String getInfo(int productId) {
		try {
			Map<String, Object> product = (Map<String, Object>) client.call(
					ResourcePath.ProductInfo, productId);

			return Utils.dump(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/**
	 * Create a new Product
	 *
	 * @param sku
	 *            - product SKU
	 * @param type
	 *            - product type (simple, configurable, etc)
	 * @param mpp
	 *            - product properties
	 * @param attributeSet
	 *            - attribute set of that product
	 * @return the created product id or -1 on error
	 */
	public Integer create(String sku, ProductType type, ProductProperties mpp,
			int attributeSet) {

		// examining whether product already exists,
		// TODO: update product in place of delete
		if (getId(sku) > -1)
			delete(sku);

		List<Object> newProduct = new LinkedList<Object>();
		newProduct.add(type.getType());
		newProduct.add(attributeSet);
		newProduct.add(sku);
		newProduct.add(mpp.getProperties());

		try {
			return Integer.parseInt((String) client.call(
					ResourcePath.ProductCreate, newProduct));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/**
	 * Create new product and return product id
	 *
	 * @param sku
	 *            product SKU
	 * @return the created product id or -1 on error
	 */
	public int create(String sku) {
		return create(sku, new ProductProperties());
	}

	/**
	 * Create new <b>SIMPLE</b> product with atributte set default (9) and
	 * return product id
	 *
	 * @param sku
	 *            product SKU
	 * @param properties
	 *            product properties
	 * @return the created product id or -1 on error
	 */
	public Integer create(String sku, ProductProperties properties) {
		return create(sku, properties, 9); // 9 = attribute set (default)
	}

	/**
	 * Create new <b>SIMPLE</b> product and return product id
	 *
	 * @param sku
	 *            product SKU
	 * @param properties
	 *            product properties
	 * @param attributeSet
	 *            attribute set
	 * @return the created product id or -1 on error
	 */
	public Integer create(String sku, ProductProperties mpp, int attributeSet) {
		return this.create(sku, ProductType.SIMPLE, mpp, attributeSet);
	}

	/**
	 * Delete product by sku
	 *
	 * @param productSku
	 *            product SKU
	 * @return the created product id or -1 on error
	 */
	public boolean delete(String productSku) {
		try {
			return (Boolean) client
					.call(ResourcePath.ProductDelete, productSku);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Delete product by id
	 *
	 * @param productId
	 *            product ID
	 * @return the created product id or -1 on error
	 */
	public boolean delete(int productId) {
		try {
			return (Boolean) client.call(ResourcePath.ProductDelete, productId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/*
	 * get product id from sku
	 */
	public int getId(String productSku) {
		try {
			Map<String, Object> productAtributes = (HashMap) client.call(
					ResourcePath.ProductInfo, productSku);

			return Integer
					.parseInt((String) productAtributes.get("product_id"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}
}
