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
		String list = pl.getList("related", "2000");
		System.out.println("*** DEBUG *** getList:" + list);
		pl.logout();
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
		boolean state = pl.assign("up_sell", "2000", new String[] { "2001", "2002", "3000", "4000" }, lp);
		System.out.println("*** DEBUG *** assign:" + state);
		pl.logout();
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
		pl.logout();
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
		pl.logout();
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
		pl.logout();
	}

	/**
	 * Test method for
	 * {@link code.google.magja.magento.product.ProductLink#getTypes()}.
	 */
	@Test
	public void testGetAttributes() {
		ProductLink pl = new ProductLink();
		String list = pl.getAttributes("related");
		System.out.println("*** DEBUG *** getTypes:" + list);
		pl.logout();
	}
}