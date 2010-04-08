package com.google.code.magja.model.product;

public enum ProductTypeEnum {

	SIMPLE, GROUPED, CONFIGURABLE, VIRTUAL, BUNDLE, DOWNLOADABLE;

	public ProductType getProductType() {
		if(this.equals(SIMPLE)) return new ProductType("Simple Product", "simple");
		else if(this.equals(GROUPED)) return new ProductType("Grouped Product", "grouped");
		else if(this.equals(CONFIGURABLE)) return new ProductType("Configurable Product", "configurable");
		else if(this.equals(VIRTUAL)) return new ProductType("Virtual Product", "virtual");
		else if(this.equals(BUNDLE)) return new ProductType("Bundle Product", "bundle");
		else if(this.equals(DOWNLOADABLE)) return new ProductType("Downloadable Product", "downloadable");
		else return new ProductType();
	}

	public static ProductType getTypeOf(String type) {
		if(type.equals(SIMPLE.getProductType().getType())) return SIMPLE.getProductType();
		else if(type.equals(GROUPED.getProductType().getType())) return GROUPED.getProductType();
		else if(type.equals(CONFIGURABLE.getProductType().getType())) return CONFIGURABLE.getProductType();
		else if(type.equals(VIRTUAL.getProductType().getType())) return VIRTUAL.getProductType();
		else if(type.equals(BUNDLE.getProductType().getType())) return BUNDLE.getProductType();
		else if(type.equals(DOWNLOADABLE.getProductType().getType())) return DOWNLOADABLE.getProductType();
		return null;
	}

}
