/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

package code.google.magja.magento.customer;

import code.google.magja.magento.Properties;

public class CustomerProperties extends Properties {

	/*
	 * Constructor
	 */
	public CustomerProperties() {
	}

	/*
	 * Setter
	 */
	public void setFirstname(String value) {
		set("firstname", value);
	}

	public void setLastname(String value) {
		set("lastname", value);
	}

	public void setEmail(String value) {
		set("email", value);
	}

	public void setPassword_hash(String value) {
		set("password_hash", value);
	}

	public void setStore_id(int value) {
		set("store_id", value);
	}

	/*
	 * 0 - Admin 1 - Main Website
	 */
	public void setWebsite_id(int value) {
		set("website_id", value);
	}
}