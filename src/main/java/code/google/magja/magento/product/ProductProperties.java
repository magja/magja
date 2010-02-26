/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

package code.google.magja.magento.product;

import code.google.magja.magento.Properties;


public class ProductProperties extends Properties {

	/*
	 * Constructor
	 */
	public ProductProperties() {
	}

	/*
	 * Setter
	 */
	public void setName(String value) {
		set("name", value);
	}

	public void setPrice(double value) {
		set("price", value);
	}

	public void setShort_description(String value) {
		set("short_description", value);
	}

	public void setDescription(String value) {
		set("description", value);
	}

	/**
	 * @param value
	 *            <p>
	 *            1 - Enabled<br>
	 *            2 - Disabled<br>
	 *            </p>
	 */
	public void setStatus(int value) {
		set("status", value);
	}

	public void setTax_class_id(int value) {
		set("tax_class_id", value);
	}

	public void setWebsites(int[] value) {
		set("websites", getStringArray(value)); // ToDo: use int array
	}

	public void setWeight(double value) {
		set("weight", value);
	}

	public void setCategories(int[] value) {
		set("categories", getStringArray(value)); // ToDo: use int array
	}
}
