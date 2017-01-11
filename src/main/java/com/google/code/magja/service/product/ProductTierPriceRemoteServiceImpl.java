package com.google.code.magja.service.product;

import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductTierPrice;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.common.collect.Lists;

/**
 * Implementation of the {@link ProductTierPriceRemoteService}
 * 
 * @author Simon Zambrovski
 */
public class ProductTierPriceRemoteServiceImpl extends GeneralServiceImpl<ProductTierPrice> implements ProductTierPriceRemoteService {

  private transient final static Logger log = LoggerFactory.getLogger(ProductTierPriceRemoteServiceImpl.class);
  private static final long serialVersionUID = -1L;

  public ProductTierPriceRemoteServiceImpl(final MagentoSoapClient soapClient) {
    super(soapClient);
  }

  @Override
  public List<ProductTierPrice> getTierPrices(final Product product) throws ServiceException {
    if (!ProductServiceUtil.validateProduct(product)) {
      throw new ServiceException("The product must have the id or the SKU set.");
    }
    final List<ProductTierPrice> result = Lists.newArrayList();
    List<Map<String, Object>> tierPrices;
    try {
      tierPrices = soapClient.callSingle(ResourcePath.ProductAttributeTierPriceInfo, (product.getId() != null ? product.getId() : product.getSku()));
    } catch (final AxisFault e) {
      if (debug) {
        log.error("Error retrieving tier prices", e);
      }
      throw new ServiceException(e.getMessage());
    }
    if (tierPrices != null) {
      for (Map<String, Object> tierPrice : tierPrices) {
        result.add(buildTierPrice(tierPrice));
      }

    }
    return result;
  }

  private ProductTierPrice buildTierPrice(final Map<String, Object> tierPrice) {
    final ProductTierPrice result = new ProductTierPrice();
    for (Map.Entry<String, Object> attribute : tierPrice.entrySet()) {
      result.set(attribute.getKey(), attribute.getValue());
    }
    return result;
  }

  @Override
  public Boolean update(final Product product, final List<ProductTierPrice> tierPrices) throws ServiceException {
    if (!ProductServiceUtil.validateProduct(product)) {
      throw new ServiceException("The product must have the id or the SKU set.");
    }

    List<Map<String, Object>> serializedPrices = Lists.newArrayList();
    for (final ProductTierPrice tierPrice : tierPrices) {
      serializedPrices.add(tierPrice.serializeToApi());
    }

    try {
      Boolean result = (Boolean) soapClient.callArgs(ResourcePath.ProductAttributeTierPriceUpdate,
          new Object[] { product.getId() != null ? product.getId() : product.getSku(), serializedPrices });
      return result;
    } catch (final AxisFault e) {
      if (debug) {
        log.error("Error updating tier prices", e);
      }
      throw new ServiceException(e.getMessage());
    }
  }

}
