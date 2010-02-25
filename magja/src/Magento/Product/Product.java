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
package Magento.Product;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Magento.Connection;
import Magento.ResourcePath;
import Magento.Utils;

public class Product extends Connection {

	/*
	 * constructor
	 */
	public Product(String user, String pass, String url) {
		super(user, pass, url);
	}

	/*
	 * get product list
	 */
	public String getList() {
		try {
			List<Map<String, Object>> productList = (List<Map<String, Object>>) client.call(ResourcePath.ProductList, "");

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
			Map<String, Object> product = (Map<String, Object>) client.call(ResourcePath.ProductInfo, sku);

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
			Map<String, Object> product = (Map<String, Object>) client.call(ResourcePath.ProductInfo, productId);

			return Utils.dump(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
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
	 * Create new product and return product id
	 * 
	 * @param sku
	 *            product SKU
	 * @param properties
	 *            product properties
	 * @return the created product id or -1 on error
	 */
	public int create(String sku, ProductProperties properties) {
		return create(sku, properties, 9); // 9 = attribute set (default)
	}

	/**
	 * Create new product and return product id
	 * 
	 * @param sku
	 *            product SKU
	 * @param properties
	 *            product properties
	 * @param attributeSet
	 *            attribute set
	 * @return the created product id or -1 on error
	 */
	public int create(String sku, ProductProperties mpp, int attributeSet) {
		// examining whether product already exists
		// ToDo: update product in place of delete
		if (getId(sku) > -1) {
			delete(sku);
		}

		// create product
		List<Object> newProduct = new LinkedList<Object>();
		newProduct.add("simple");
		newProduct.add(attributeSet);
		newProduct.add(sku);
		newProduct.add(mpp.getProperties());

		// create product
		try {
			return Integer.parseInt((String) client.call(ResourcePath.ProductCreate, newProduct));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
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
			return (Boolean) client.call(ResourcePath.ProductDelete, productSku);
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
			Map<String, Object> productAtributes = (HashMap) client.call(ResourcePath.ProductInfo, productSku);

			return Integer.parseInt((String) productAtributes.get("product_id"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/*
	 * main
	 */
	public static void main(String[] args) {
		// login
		Product p = new Product("soap", "test123", "http://192.168.1.88/magento/index.php/api/");

		// test procuckt list
		String productList = p.getList();
		System.out.println("*** DEBUG *** productList:" + productList);

		// get product info
		String productInfo = p.getInfo("n2610");
		System.out.println("*** DEBUG *** productInfo:" + productInfo);

		// create product
		int productId = p.create("test_item_2");
		System.out.println("*** DEBUG *** createProduct:" + productId);

		// logout
		p.logout();
	}
}
