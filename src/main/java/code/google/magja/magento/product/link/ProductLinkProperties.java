/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */
package code.google.magja.magento.product.link;

import code.google.magja.magento.Properties;

public class ProductLinkProperties extends Properties {

	/*
	 * Constructor
	 */
	public ProductLinkProperties() {
	}

	public void setQty(double value) {
		set("qty", value);
	}
	
	public void setPosition(int value) {
		set("position", value);
	}
}
