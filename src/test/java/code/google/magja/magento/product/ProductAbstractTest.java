/**
 *
 */
package code.google.magja.magento.product;

/**
 * @author andre
 *
 */
public abstract class ProductAbstractTest {

	protected ProductProperties generateProductProperties() {
		ProductProperties mpp = new ProductProperties();
		mpp.setName("Remote Testing Inserting Product");
		mpp.setDescription("This is a Description of remote " +
				"testing to insert a new Product");
		mpp.setShort_description("This is a Short Description of " +
				"remote testing to insert a new Product");

		mpp.setPrice(new Double(120.34));
		mpp.set("cost", new Double(64.68));

		mpp.setStatus(1);
		mpp.setWeight(new Double(0.300));
		int[] websites = { 1 };
		mpp.setWebsites(websites);
		mpp.setTax_class_id(1);

		return mpp;
	}

}
