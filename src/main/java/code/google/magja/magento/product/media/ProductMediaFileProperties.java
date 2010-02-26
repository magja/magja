/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */
package code.google.magja.magento.product.media;

import org.apache.axis2.util.Base64;

import code.google.magja.magento.Properties;
import code.google.magja.magento.Utils;



public class ProductMediaFileProperties extends Properties {

	/*
	 * Constructor
	 */
	public ProductMediaFileProperties() {
	}

	/*
	 * Setter
	 */
	public void setContent(String value) {
		set("content", value);
	}

	public void setMime(String value) {
		set("mime", value);
	}

	public void createImageJpeg(String pathToFile) {
		// read file
		byte buf[] = Utils.readFile(pathToFile);

		// set file atributes
		setContent(Base64.encode(buf));
		setMime("image/jpeg");
	}
}
