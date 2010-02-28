/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

package code.google.magja.magento.customer.address;

import code.google.magja.magento.Properties;

public class CustomerAddressProperties extends Properties {

	/*
	 * Constructor
	 */
	public CustomerAddressProperties() {
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

	public void setCity(String value) {
		set("city", value);
	}

	public void setCountryId(String value) {
		set("country_id", value);
	}

	public void setTelephone(String value) {
		set("telephone", value);
	}

	public void setPostcode(String value) {
		set("postcode", value);
	}

	public void setStreet(String[] value) {
		set("street", value);
	}

	public void setRegionId(int value) {
		set("region_id", value);
	}

	public void setRegion(String value) {
		set("region", value);
	}

	public void setIsDefaultBilling(int value) { // todo: change to boolean
		set("is_default_billing", value);
	}

	public void setIsDefaultShipping(int value) { // todo: change to boolean
		set("is_default_shipping", value);
	}
}