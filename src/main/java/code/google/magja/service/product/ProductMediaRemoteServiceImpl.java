/**
 *
 */
package code.google.magja.service.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;
import code.google.magja.model.product.Product;
import code.google.magja.model.product.ProductMedia;
import code.google.magja.service.GeneralServiceImpl;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class ProductMediaRemoteServiceImpl extends
		GeneralServiceImpl<ProductMedia> implements ProductMediaRemoteService {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.product.ProductMediaRemoteService#delete(code
	 * .google.magja.model.product.ProductMedia)
	 */
	@Override
	public void delete(ProductMedia productMedia) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @seecode.google.magja.service.product.ProductMediaRemoteService#
	 * getByProductAndFile(code.google.magja.model.product.Product,
	 * java.lang.String)
	 */
	@Override
	public ProductMedia getByProductAndFile(Product product, String file)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.product.ProductMediaRemoteService#listByProduct
	 * (code.google.magja.model.product.Product)
	 */
	@Override
	public List<ProductMedia> listByProduct(Product product)
			throws ServiceException {

		List<ProductMedia> result = new ArrayList<ProductMedia>();

		if (product.getId() == null && product.getSku() == null)
			throw new ServiceException(
					"The product must have the id or the sku seted for list medias");

		List<Map<String, Object>> medias = null;
		try {
			medias = (List<Map<String, Object>>) soapClient.call(
					ResourcePath.ProductAttributeMediaList,
					(product.getId() != null ? product.getId() : product
							.getSku()));
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if (medias == null)
			return null;

		for (Map<String, Object> media : medias) {

			ProductMedia prd_media = new ProductMedia();

			for (Map.Entry<String, Object> att : media.entrySet())
				prd_media.set(att.getKey(), att.getValue());

			if(media.get("types") != null) {
				prd_media.setTypes(new HashSet<ProductMedia.Type>());
				List<String> types = (List<String>) media.get("types");
				for (String type : types)
					prd_media.getTypes().add(ProductMedia.Type.getValueOfString(type));
			}

			result.add(prd_media);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.product.ProductMediaRemoteService#save(code
	 * .google.magja.model.product.ProductMedia)
	 */
	@Override
	public void save(ProductMedia productMedia) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
