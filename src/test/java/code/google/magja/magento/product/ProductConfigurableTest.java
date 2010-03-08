package code.google.magja.magento.product;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ProductConfigurableTest extends ProductAbstractTest {

	@Test
	public void testCreateProduct() {

		// first, we have to create a new attribute set




		Product p = new Product();
		ProductProperties mpp = generateProductProperties();

		Integer productId = p.create("CONF-PRD-TEST", ProductType.CONFIGURABLE, mpp, 4);
		assertFalse((new Integer(-1)).equals(productId));

	}

}
