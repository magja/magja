/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

package code.google.magja.magento.product.media;

import java.util.Map;

import code.google.magja.magento.Properties;


public class ProductMediaProperties extends Properties {

	/*
	 * Constructor
	 */
	public ProductMediaProperties() {
	}

	/*
	 * Setter
	 */
	public void setExclude(int value) {
		set("exclude", value);
	}

	public void setFile(Map<String, Object> value) {
		set("file", value);
	}

	public void setLabel(String value) {
		set("label", value);
	}

	public void setPosition(int value) {
		set("position", value);
	}

	/**
	 *
	 * @param value
	 *
	 *            Possible values: thumbnail, small_image, image
	 */
	public void setTypes(String[] value) {
		set("types", value);
	}
}
