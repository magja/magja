/**
 *
 */
package com.google.code.magja.model.order;

import com.google.code.magja.model.address.Address;

/**
 * @author andre
 *
 */
public class OrderAddress extends Address {

	/* (non-Javadoc)
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
