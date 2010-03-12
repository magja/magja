/**
 *
 */
package code.google.magja.service.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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

	/**
	 * Build the object ProductMedia with the Map returned by the api
	 *
	 * @param map
	 * @return ProductMedia
	 */
	private ProductMedia buildProductMedia(Map<String, Object> map) {
		ProductMedia prd_media = new ProductMedia();

		for (Map.Entry<String, Object> att : map.entrySet())
			prd_media.set(att.getKey(), att.getValue());

		if (map.get("types") != null) {
			prd_media.setTypes(new HashSet<ProductMedia.Type>());
			List<String> types = (List<String>) map.get("types");
			for (String type : types)
				prd_media.getTypes().add(
						ProductMedia.Type.getValueOfString(type));
		}

		return prd_media;
	}

	/**
	 * test if the product is not null, and if the product id or sku is not null
	 *
	 * @param product
	 * @return
	 */
	private Boolean validadeProduct(Product product) {
		if (product == null)
			return false;
		else if (product.getId() != null || product.getSku() != null)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.product.ProductMediaRemoteService#delete(code
	 * .google.magja.model.product.ProductMedia)
	 */
	@Override
	public void delete(ProductMedia productMedia) throws ServiceException {
		if (!validadeProduct(productMedia.getProduct()))
			throw new ServiceException(
					"the product attribute for the media must be setted.");

		List<Object> params = new LinkedList<Object>();
		params.add((productMedia.getProduct().getId() != null ? productMedia
				.getProduct().getId() : productMedia.getProduct().getSku()));
		params.add(productMedia.getFile());

		Boolean success = false;
		try {
			success = (Boolean) soapClient.call(
					ResourcePath.ProductAttributeMediaRemove, params);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if (!success)
			throw new ServiceException("Error deleting the Product Media");
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

		if (!validadeProduct(product))
			throw new ServiceException(
					"the product for the media must be setted.");

		List<Object> params = new LinkedList<Object>();
		params.add((product.getId() != null ? product.getId() : product
				.getSku()));
		params.add(file);

		Map<String, Object> media = null;
		try {
			media = (Map<String, Object>) soapClient.call(
					ResourcePath.ProductAttributeMediaInfo, params);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return buildProductMedia(media);
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

		if (!validadeProduct(product))
			throw new ServiceException(
					"The product must have the id or the sku seted for list medias");

		List<ProductMedia> result = new ArrayList<ProductMedia>();

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

		for (Map<String, Object> media : medias)
			result.add(buildProductMedia(media));

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
		if (!validadeProduct(productMedia.getProduct()))
			throw new ServiceException(
					"the product attribute for the media must be setted.");

		if (productMedia.getImage() == null)
			throw new ServiceException("the image is null.");

		if (productMedia.getImage().getData() == null)
			throw new ServiceException("invalid binary data for the image.");

		try {
			String result = (String) soapClient.call(
					ResourcePath.ProductAttributeMediaCreate, productMedia
							.serializeToApi());

			productMedia.setFile(result);

		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
