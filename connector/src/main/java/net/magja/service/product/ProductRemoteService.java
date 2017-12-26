package net.magja.service.product;

import net.magja.model.product.Product;
import net.magja.model.product.ProductRefMagja;
import net.magja.model.product.ProductType;
import net.magja.model.product.ProductUpdatePrice;
import net.magja.service.GeneralService;
import net.magja.service.ServiceException;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Product service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface ProductRemoteService extends GeneralService<Product> {

  public static enum Dependency {
    CATEGORIES, MEDIAS, LINKS, TYPES, ATTRIBUTE_SET, INVENTORY
  };

  /**
   * Get the product from Magento with the specified sku
   *
   * @param sku
   * @return Product
   * @throws ServiceException
   */
  Product getBySku(String sku) throws ServiceException;

  /**
   * Get the product from Magento with the specified sku
   *
   * @param sku
   * @param dependencies
   * @return Product
   * @throws ServiceException
   */
  Product getBySku(String sku, boolean dependencies) throws ServiceException;

  Product getBySku(String sku, Set<String> attributes, boolean dependencies) throws ServiceException;

  Product getBySku(String sku, Set<String> attributes) throws ServiceException;

  Product getBySku(String sku, Set<String> attributes, Set<Dependency> dependencies) throws ServiceException;

  /**
   * Get the product from Magento with the specified id
   *
   * @param id
   * @return Product
   * @throws ServiceException
   */
  Product getById(Integer id) throws ServiceException;

  Product getById(Integer id, Set<String> attributes) throws ServiceException;

  /**
   * List all products from Magento, just the basic attributes, with their
   * dependencies, low performance
   *
   * @return list of all products
   * @throws ServiceException
   */
  List<Product> listAll() throws ServiceException;

  /**
   * Use this to list all product, just the basic attributes, without any
   * dependencies (Categories, Inventory, etc), more performance
   *
   * @return List<Product>
   * @throws ServiceException
   */
  List<Product> listAllNoDep() throws ServiceException;

  /**
   * Use this to list all product, just the basic attributes, without any
   * dependencies (Categories, Inventory, etc), more performance. Similar to
   * listAllNoDep() but you can specify manually more attributes to select.
   * Additional attribute values will be saved inside Product.getAttributes()
   * property.
   *
   * @return List<Product>
   * @throws ServiceException
   */
  List<Product> listAllPlus(Set<String> attributesToSelect) throws ServiceException;

  /**
   * Create a product to Magento.
   *
   * @param product
   */
  void add(Product product) throws ServiceException, NoSuchAlgorithmException;

  /**
   * Create a product to Magento.
   *
   * @param product
   */
  void add(Product product, String storeView) throws ServiceException, NoSuchAlgorithmException;

  /**
   * Update a product.
   *
   * @param product
   *          The new product data object.
   * @param existingProduct
   *          The old product data object. Used to compare which data needs to
   *          be updated.
   */
  void update(Product product, Product existingProduct) throws ServiceException, NoSuchAlgorithmException;

  /**
   * Update a product.
   *
   * @param product
   *          The new product data object.
   * @param existingProduct
   *          The old product data object. Used to compare which data needs to
   *          be updated.
   */
  void update(Product product, Product existingProduct, String storeView) throws ServiceException, NoSuchAlgorithmException;

  /**
   * Save a product to the Magento, if the id attribute is null, then will
   * create a new product, otherwise will update the product with that id. If an
   * "existingProduct" is assigned, its categories will be copied to the
   * upserted "product".
   *
   * @param product
   * @param existingProduct
   * @throws ServiceException
   * @deprecated Use either {@link ProductRemoteService#add(Product)} or
   *             {@link ProductRemoteService#update(Product)}.
   */
  @Deprecated
  void save(Product product, Product existingProduct) throws ServiceException, NoSuchAlgorithmException;

  /**
   * Save a product to the Magento, if the id attribute is null, then will
   * create a new product, otherwise will update the product with that id
   *
   * @param product
   * @param storeView
   * @throws ServiceException
   * @deprecated Use either {@link ProductRemoteService#add(Product)} or
   *             {@link ProductRemoteService#update(Product)}.
   */
  @Deprecated
  void save(Product product, Product existingProduct, String storeView) throws ServiceException, NoSuchAlgorithmException;

  /**
   * Remove a product from magento with the specified id
   *
   * @param id
   * @throws ServiceException
   */
  void delete(Integer id) throws ServiceException;

  /**
   * remove a product from magento with the specified sku
   *
   * @param sku
   * @throws ServiceException
   */
  void delete(String sku) throws ServiceException;

  /**
   * Remove all product from magento
   *
   * @throws ServiceException
   */
  void deleteAll() throws ServiceException;

  void deleteWithEmptyCategory(String sku) throws ServiceException;

  /**
   * @return List of all ProductTypes from magento api
   * @throws ServiceException
   */
  List<ProductType> listAllProductTypes() throws ServiceException;

  /**
   * Use to get the inventory of the products on the Set specified
   *
   * @param products
   * @throws ServiceException
   */
  void getInventoryInfo(Set<Product> products) throws ServiceException;

  /**
   * Update a quantity of the product specified, the product must have at least
   * the id or the sku
   *
   * @param product
   * @throws ServiceException
   */
  void updateInventory(Product product) throws ServiceException;

  List<Product> getWithoutCategory() throws ServiceException;

  void setConfigurableAttributes(String productSku, Map<String, String> attributeNames) throws ServiceException;

  void setAssociatedProducts(String productSku, Map<String, String> childProducts) throws ServiceException;

  /**
   * Activates the store default manage stock property
   * @param product product with SKU or ID set.
   * @throws ServiceException on any error.
   */
  void unsetManageStock(Product product) throws ServiceException;

  /**
   * Sets manage stock value of a given product to true.
   * <p>This call is a shortcut for ProductRemoteService.setManageStock(product, true)</p>
   * @param product product with SKU or ID set.
   * @throws ServiceException on any error.
   */
  void setManageStock(Product product) throws ServiceException;

  /**
   * Sets manage stock value of a given product to desired value.
   * <p>Please note that this method will disable the usage of store default for this property.
   * If you want to use the default, please use {@link ProductRemoteService#unsetManageStock(Product)}</p>
   * @param product product with SKU or ID set.
   * @param manageStock desired value.
   * @throws ServiceException on any error.
   */
  void setManageStock(Product product, boolean manageStock) throws ServiceException;

  Product getBySkuWithCategories(String sku) throws ServiceException;

  /**
   * Get a list of products updated since the given date
   *
   * @param date
   * @return
   * @throws ServiceException
   */
  List<Product> listUpdatedBetween(Date from, Date to) throws ServiceException;

  /**
   * Retrieve the URL Path, Name, and 50x50 image for a list of products.
   *
   * @param array
   *          $skus Array of SKUs.
   * @return array Associative array containing url_path, name, image_50x50,
   *         shop_id.
   */
  Map<String, ProductRefMagja> getRefs(List<String> skus) throws ServiceException;

  /**
   * Retrieve the URL Path, Name, and 50x50 image for a list of products.
   *
   * @param array
   *          $skus Array of SKUs.
   * @return array Associative array containing url_path, name, image_50x50,
   *         shop_id.
   */
  Map<String, Map<String, String>> getRefsMap(List<String> skus) throws ServiceException;

  /**
   * Updates prices for a list of products.
   * @param products list of products.
   * @throws ServiceException on any error.
   */
  void updatePrice(List<ProductUpdatePrice> products) throws ServiceException;

  void update(Product product, Product existingProduct, String storeView, Set<Dependency> dependencies) throws ServiceException, NoSuchAlgorithmException;

  void updateInventory(Product product, Map<String, Object> properties) throws ServiceException;

}
