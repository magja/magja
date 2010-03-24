/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

package com.google.code.magja.magento;

import java.util.HashMap;
import java.util.Map;

public class Properties {

	/*
	 * Variables
	 */
	private Map<String, Object> properties = new HashMap<String, Object>();

	/*
	 * Check and set property
	 */
	public void set(String name, Object value) {
		if (value != null) {
			properties.put(name, value);
		}
	}

	/*
	 * Get property
	 */
	public Object get(String name) {
		return properties.get(name);
	}

	/*
	 * Getter
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/*
	 * convert int array to string array
	 */
	protected String[] getStringArray(int[] arrayIn) {
		String[] arrayOut = new String[arrayIn.length];
		for (int i = 0; i < arrayIn.length; i++) {
			arrayOut[i] = arrayIn[i] + "";
		}

		return arrayOut;
	}
}
