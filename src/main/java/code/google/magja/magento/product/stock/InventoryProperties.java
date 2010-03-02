/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

package code.google.magja.magento.product.stock;

import code.google.magja.magento.Properties;

public class InventoryProperties extends Properties {

	/*
	 * Constructor
	 */
	public InventoryProperties() {
	}

	/*
	 * Setter
	 */
	public void setQty(int value) {
		set("qty", value);
	}

	public void setIsInStock(int value) {
		set("is_in_stock", value);
	}
}
