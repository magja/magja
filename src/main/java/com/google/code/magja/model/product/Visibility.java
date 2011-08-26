/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

public enum Visibility {

	NOT_VISIBLE_INDIVIDUALLY(1), CATALOG(2), SEARCH(3), CATALOG_SEARCH(4);

	private Integer value;

	private Visibility(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

}
