/**
 * @author andre
 *
 */
package net.magja.model.order;

import net.magja.model.address.Address;

public class OrderAddress extends Address {

	private static final long serialVersionUID=-6252927719541675884L;

	/* (non-Javadoc)
	 * @see net.magja.magja.model.BaseMagentoModel#serializeToApi()
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
