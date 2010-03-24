/**
 *
 */
package code.google.magja.model.order;

import code.google.magja.model.address.Address;

/**
 * @author andre
 *
 */
public class OrderAddress extends Address {

	/* (non-Javadoc)
	 * @see code.google.magja.model.BaseMagentoModel#serializeToApi()
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
