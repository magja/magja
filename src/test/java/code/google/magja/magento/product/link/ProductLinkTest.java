package code.google.magja.magento.product.link;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class ProductLinkTest {

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.ProductLink#getList()}.
	 */
	@Test
	public void testGetList() {
		ProductLink pl = new ProductLink();
		String list = pl.getList("related", "32");
		System.out.println("*** DEBUG *** getList:" + list);

	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.ProductLink#assign()}.
	 */
	@Test
	public void testAssign() {
		ProductLink pl = new ProductLink();
		ProductLinkProperties lp = new ProductLinkProperties();
		lp.setQty(10.0);
		boolean state = pl.assign("up_sell", "17", new String[] { "18", "19", "20", "21" }, lp);
		System.out.println("*** DEBUG *** assign:" + state);

	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.ProductLink#update()}.
	 */
	@Test
	public void testUpdate() {
		ProductLink pl = new ProductLink();
		ProductLinkProperties lp = new ProductLinkProperties();
		lp.setPosition(1);
		boolean state = pl.update("up_sell", "2000", "4000", lp);
		System.out.println("*** DEBUG *** update:" + state);

	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.ProductLink#remove()}.
	 */
	@Test
	public void testRemove() {
		ProductLink pl = new ProductLink();
		boolean state = pl.remove("related", "2000", "3000");
		System.out.println("*** DEBUG *** remove:" + state);

	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.ProductLink#getTypes()}.
	 */
	@Test
	public void testGetTypes() {
		ProductLink pl = new ProductLink();
		String list = pl.getTypes();
		System.out.println("*** DEBUG *** getTypes:" + list);

	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.ProductLink#getTypes()}.
	 */
	@Test
	public void testGetAttributes() {
		ProductLink pl = new ProductLink();
		String list = pl.getAttributes("related");
		System.out.println("*** DEBUG *** getTypes related:" + list);

		list = pl.getAttributes("up_sell");
		System.out.println("*** DEBUG *** getTypes up_sell:" + list);

		list = pl.getAttributes("cross_sell");
		System.out.println("*** DEBUG *** getTypes cross_sell:" + list);

		list = pl.getAttributes("grouped");
		System.out.println("*** DEBUG *** getTypes grouped:" + list);

	}
}