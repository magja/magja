package com.google.code.magja.service.product;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.product.Product;
import com.google.code.magja.model.product.ProductLink;
import com.google.code.magja.model.product.ProductLink.LinkType;
import com.google.code.magja.model.product.ProductType;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.SoapClient;

/**
 * Product link service implementation.
 * @author andre
 * @author Simon Zambrovski
 */
public class ProductLinkRemoteServiceImpl extends GeneralServiceImpl<ProductLink> implements ProductLinkRemoteService {

  private static final long serialVersionUID = 28223743577747312L;

  public ProductLinkRemoteServiceImpl(SoapClient soapClient) {
    super(soapClient);
  }

  private ProductLink buildProductLink(Map<String, Object> map, LinkType linkType) {
    ProductLink link = new ProductLink();

    link.setLinkType(linkType);

    for (Map.Entry<String, Object> att : map.entrySet()) {
      link.set(att.getKey(), att.getValue());
    }

    if (map.get("type") != null) {
      link.setProductType(ProductType.getType((String) map.get("type")));
    }
    return link;
  }

  private Object[] buildLinkToPersist(Product product, ProductLink link) {
    return new Object[] { link.getLinkType().toString().toLowerCase(), product.getId() != null ? product.getId() : product.getSku(),
        link.getId() != null ? link.getId() : link.getSku(), link.serializeToApi() };
  }

  @Override
  public void assign(Product product, ProductLink link) throws ServiceException {
    if (!ProductServiceUtil.validateProduct(product)) {
      throw new ServiceException("the product id or sku must be set.");
    }
    if (!ProductServiceUtil.validateProductLink(link)) {
      throw new ServiceException("you must specify the products to be assigned.");
    }

    try {
      soapClient.callArgs(ResourcePath.ProductLinkAssign, buildLinkToPersist(product, link));
    } catch (AxisFault e) {
      if (debug) {
        e.printStackTrace();
      }
      throw new ServiceException(e.getMessage());
    }

  }

  @Override
  public Set<ProductLink> list(Product product) throws ServiceException {

    if (!ProductServiceUtil.validateProduct(product)) {
      throw new ServiceException("you must specify a product");
    }
    Set<ProductLink> links = new HashSet<ProductLink>();

    for (LinkType linkType : LinkType.values()) {
      links.addAll(list(linkType, product));
    }
    return links;
  }

  @Override
  public Set<ProductLink> list(LinkType linktype, Product product) throws ServiceException {

    if (!ProductServiceUtil.validateProduct(product)) {
      throw new ServiceException("you must specify a product");
    }
    if (linktype == null) {
      throw new ServiceException("you must specify a link type");
    }

    Set<ProductLink> links = new HashSet<ProductLink>();

    List<Map<String, Object>> results = null;
    try {
      results = soapClient.callArgs(ResourcePath.ProductLinkList,
          new Object[] { linktype.toString().toLowerCase(), product.getId() != null ? product.getId() : product.getSku() });
    } catch (AxisFault e) {
      if (debug) {
        e.printStackTrace();
      }
      throw new ServiceException(e.getMessage());
    }

    if (results != null) {
      for (Map<String, Object> result : results) {
        links.add(buildProductLink(result, linktype));
      }
    }
    return links;
  }

  @Override
  public void remove(Product product, ProductLink link) throws ServiceException {
    if (!ProductServiceUtil.validateProduct(product)) {
      throw new ServiceException("the product id or sku must be set");
    }
    if (!ProductServiceUtil.validateProductLink(link)) {
      throw new ServiceException("you must specify the products to be assigned");
    }
    try {
      soapClient.callArgs(ResourcePath.ProductLinkRemove, new Object[] { link.getLinkType().toString().toLowerCase(),
          product.getId() != null ? product.getId() : product.getSku(), link.getId() != null ? link.getId() : link.getSku() });
    } catch (AxisFault e) {
      if (debug) {
        e.printStackTrace();
      }
      throw new ServiceException(e.getMessage());
    }

  }

  @Override
  public void update(Product product, ProductLink link) throws ServiceException {

    if (!ProductServiceUtil.validateProduct(product)) {
      throw new ServiceException("the product id or sku must be set");
    }
    if (!ProductServiceUtil.validateProductLink(link)) {
      throw new ServiceException("you must specify the products to be assigned");
    }

    try {
      soapClient.callArgs(ResourcePath.ProductLinkUpdate, buildLinkToPersist(product, link));
    } catch (AxisFault e) {
      if (debug) {
        e.printStackTrace();
      }
      throw new ServiceException(e.getMessage());
    }
  }

}
