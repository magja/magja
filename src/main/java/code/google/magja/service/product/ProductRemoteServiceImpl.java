/**
 *
 */
package code.google.magja.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;
import code.google.magja.model.category.Category;
import code.google.magja.model.product.Product;
import code.google.magja.model.product.ProductAttributeSet;
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

		for (Map<String, Object> map : productList) {
			Product product = new Product();

			// populate the basic fields
			for (Map.Entry<String, Object> attribute : map.entrySet())
				product.set(attribute.getKey(), attribute.getValue());

			// set the attributeSet for the product
			if(map.get("set") != null) {

				ProductAttributeSet prdAttSet = new ProductAttributeSet();
				Integer set_id = Integer.parseInt((String) map.get("set"));

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
								for (Map.Entry<String, Object> att : map.entrySet())
									prdAttSet.set(att.getKey(), att.getValue());

								product.setAttributeSet(prdAttSet);

								break;
							}
						}
					}
				}
			}

			// categories
			if(map.get("category_ids") != null) {
				List<Object> ids = (List<Object>) map.get("category_ids");
				for (Object obj : ids) {
					Integer id = Integer.parseInt((String) obj);
					Category category = categoryRemoteService.getByIdClean(id);
					product.getCategories().add(category);
				}
			}

			// finally, add the product to result list
			products.add(product);
		}

		return products;
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.product.ProductRemoteService#save(code.google.magja.model.product.Product)
	 */
	@Override
	public Product save(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
