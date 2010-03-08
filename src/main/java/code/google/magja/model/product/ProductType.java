package code.google.magja.model.product;

public enum ProductType {

	SIMPLE, GROUPED, CONFIGURABLE, VIRTUAL, BUNDLE, DOWNLOADABLE;

	public String getLabel() {
		if(this.equals(SIMPLE)) return "Simple Product";
		else if(this.equals(GROUPED)) return "Grouped Product";
		else if(this.equals(CONFIGURABLE)) return "Configurable Product";
		else if(this.equals(VIRTUAL)) return "Virtual Product";
		else if(this.equals(BUNDLE)) return "Bundle Product";
		else if(this.equals(DOWNLOADABLE)) return "Downloadable Product";
		else return "";
	}

	public String getType() {
		if(this.equals(SIMPLE)) return "simple";
		else if(this.equals(GROUPED)) return "grouped";
		else if(this.equals(CONFIGURABLE)) return "configurable";
		else if(this.equals(VIRTUAL)) return "virtual";
		else if(this.equals(BUNDLE)) return "bundle";
		else if(this.equals(DOWNLOADABLE)) return "downloadable";
		else return "";
	}
}
