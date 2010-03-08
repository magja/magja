/**
 *
 */
package code.google.magja.service.product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;
import code.google.magja.model.category.Category;
import code.google.magja.model.product.Product;
import code.google.magja.model.product.ProductAttributeSet;
import code.google.magja.model.product.ProductType;
import code.google.magja.service.GeneralServiceImpl;
import code.google.magja.service.ServiceException;
import code.google.magja.service.category.CategoryRemoteService;

/**
 * @author andre
 *
 */
public class ProductRemoteServiceImpl extends GeneralServiceImpl<Product> implements ProductRemoteService {

	private CategoryRemoteService categoryRemoteService;

	/* (non-Javadoc)
	 * @see code.google.magja.service.product.ProductRemoteService#setCategoryRemoteService(code.google.magja.service.category.CategoryRemoteService)
	 */
	@Override
	public void setCategoryRemoteService(
			CategoryRemoteService categoryRemoteService) {
		this.categoryRemoteService = categoryRemoteService;
	}



	/* (non-Javadoc)
	 * @see code.google.magja.service.product.ProductRemoteService#getBySku(java.lang.String)
	 */
	@Override
	public Product getBySku(String sku) throws ServiceException {

		Map<String, Object> mpp;
		try {
			mpp = (Map<String, Object>) soapClient.call(ResourcePath.ProductInfo, sku);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if(mpp == null) return null;
		else return buildProduct(mpp);
	}



	/* (non-Javadoc)
	 * @see code.google.magja.service.product.ProductRemoteService#getById(java.lang.Integer)
	 */
	@Override
	public Product getById(Integer id) throws ServiceException {

		Map<String, Object> mpp;
		try {
			mpp = (Map<String, Object>) soapClient.call(ResourcePath.ProductInfo, id);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if(mpp == null) return null;
		else return buildProduct(mpp);
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.product.ProductRemoteService#listAll()
	 */
	@Override
	public List<Product> listAll() throws ServiceException {

		List<Product> products = new ArrayList<Product>();

		List<Map<String, Object>> productList;

		try {
			productList = (List<Map<String, Object>>) soapClient.call(ResourcePath.ProductList, "");
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if(productList == null) return products;

		for (Map<String, Object> mpp : productList)
			products.add(buildProduct(mpp));

		return products;
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.product.ProductRemoteService#save(code.google.magja.model.product.Product)
	 */
	@Override
	public Product save(Product product) throws ServiceException {

		if(product.getId() == null) {

			// means its a new product
			try {

				List<Object> newProduct = (LinkedList<Object>) product.serializeToApi();

				Integer id = Integer.parseInt((String) soapClient.call(ResourcePath.ProductCreate, newProduct));
				if(id > -1) product.setId(id);
				else throw new ServiceException("Error inserting new Product");

			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			} catch (AxisFault e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

		} else {
			// TODO implement the update method
		}

		return product;
	}

	private Product buildProduct(Map<String, Object> mpp) throws ServiceException {
		Product product = new Product();

		// populate the basic fields
		for (Map.Entry<String, Object> attribute : mpp.entrySet())
			product.set(attribute.getKey(), attribute.getValue());

		// set product type
		if(mpp.get("type") != null)
			product.setType(ProductType.getTypeOf((String) mpp.get("type")));

		// set the attributeSet
		if(mpp.get("set") != null)
			product.setAttributeSet( getAttributeSet((String) mpp.get("set")) );

		// categories - dont get the full tree, only basic info of categories
		if(mpp.get("category_ids") != null)
			product.getCategories().addAll(getCategoriesBasicInfo((List<Object>) mpp.get("category_ids")));

		return product;
	}

	/**
	 * @param ids
	 * @return list of categories with specified ids, just the basic info
	 * @throws ServiceException
	 */
	private List<Category> getCategoriesBasicInfo(List<Object> ids) throws ServiceException {
		List<Category> categories = new ArrayList<Category>();
		for (Object obj : ids) {
			Integer id = Integer.parseInt((String) obj);
			Category category = categoryRemoteService.getByIdClean(id);
			categories.add(category);
		}
		return categories;
	}

	/**
	 * @param id
	 * @return the ProductAttributeSet with the specified id
	 * @throws ServiceException
	 */
	private ProductAttributeSet getAttributeSet(String id) throws ServiceException {

		ProductAttributeSet prdAttSet = new ProductAttributeSet();
		Integer set_id = Integer.parseInt(id);

		// if are the default attribute set, that not list on the api, so we have to set manually
		if(set_id.equals(soapClient.getConfig().getDefaultAttributeSetId())) {

			prdAttSet.setId(set_id);
			prdAttSet.setName("Default");

		} else {
			List<Map<String, Object>> setList;
			try {
				setList = (List<Map<String, Object>>) soapClient.call(ResourcePath.ProductAttributeSetList, "");
			} catch (AxisFault e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

			if(setList != null) {
				for (Map<String, Object> set : setList) {
					if(set.get("set_id").equals(set_id)) {

						for (Map.Entry<String, Object> att : set.entrySet())
							prdAttSet.set(att.getKey(), att.getValue());

						break;
					}
				}
			}
		}


		return prdAttSet;
	}

}
