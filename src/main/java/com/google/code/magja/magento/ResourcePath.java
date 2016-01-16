/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Magento Core API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api
 */
package com.google.code.magja.magento;

public enum ResourcePath {

    CategoryTree("catalog_category.tree"),
    CategoryLevel("catalog_category.level"),
    CategoryInfo("catalog_category.info"),
    CategoryCreate("catalog_category.create"),
    CategoryDelete("catalog_category.delete"),
    CategoryUpdate("catalog_category.update"),
    CategoryListPaths("catalog_category.list_paths"),

    CategoryRemoveProduct("catalog_category.removeProduct"),
    CategoryAssignProduct("catalog_category.assignProduct"),
    CategoryAssignedProducts("catalog_category.assignedProducts"),
    CategoryAttributeCurrentStore("category_attribute.currentStore"),
    CategoryAttributeList("category_attribute.list"),
    CategoryAttributeOptions("category_attribute.options"),
    
    ProductList("catalog_product.list"),
    ProductListPlus("catalog_product.list_plus"),
    ProductInfo("catalog_product.info"),
    ProductGetRefs("catalog_product.get_refs"),

    ProductConfigurableAttributes("catalog_product_configurable.setConfigurableAttributes"),
    ProductAssociateChildren("catalog_product_configurable.associateSimpleChildren"),

    ProductCreate("catalog_product.create"),
    ProductUpdate("catalog_product.update"),
    ProductDelete("catalog_product.delete"),
    ProductUpdatePrice("catalog_product.update_price"),

    ProductTypeList("catalog_product_type.list"),

    ProductAttributeSetList("catalog_product_attribute_set.list"),
    ProductAttributeList("catalog_product_attribute.list"),
    ProductAttributeInfo("catalog_product_attribute.info"),
    ProductAttributeCreate("catalog_product_attribute.create"),
    ProductAttributeAdd("product_attribute_option.create"),
    ProductAttributeDelete("catalog_product_attribute.delete"),
    ProductAttributeOptions("catalog_product_attribute.options"),
    ProductAttributeAddOptions("catalog_product_attribute.addoptions"),

    ProductAttributeMediaList("catalog_product_attribute_media.list"),
    ProductAttributeMediaInfo("catalog_product_attribute_media.info"),
    ProductAttributeMediaMd5("catalog_product_attribute_media.md5"),
    ProductAttributeMediaTypes("catalog_product_attribute_media.types"),
    ProductAttributeMediaCreate("catalog_product_attribute_media.create"),
    ProductAttributeMediaUpdate("catalog_product_attribute_media.update"),
    ProductAttributeMediaRemove("catalog_product_attribute_media.remove"),

    ProductStockList("product_stock.list"),
    ProductStockUpdate("product_stock.update"),

    ProductLinkList("product_link.list"),
    ProductLinkAssign("product_link.assign"),
    ProductLinkUpdate("product_link.update"),
    ProductLinkRemove("product_link.remove"),
    ProductLinkTypes("product_link.types"),
    ProductLinkAttributes("product_link.attributes"),

    CustomerList("customer.list"),
    CustomerCreate("customer.create"),
    CustomerInfo("customer.info"),
    CustomerUpdate("customer.update"),
    CustomerDelete("customer.delete"),
    CustomerAddressList("customer_address.list"),
    CustomerAddressCreate("customer_address.create"),
    CustomerAddressInfo("customer_address.info"),
    CustomerAddressUpdate("customer_address.update"),
    CustomerAddressDelete("customer_address.delete"),
    CustomerGroupList("customer_group.list"),

    SalesOrderList("sales_order.list"),
    SalesOrderInfo("sales_order.info"),
    SalesOrderAddComment("sales_order.addComment"),
    SalesOrderHold("sales_order.hold"),
    SalesOrderUnhold("sales_order.unhold"),
    SalesOrderCancel("sales_order.cancel"),
    SalesOrderCreate("sales_order.create"),
    SalesOrderCreateEx("sales_order.create_ex"),
    SalesOrderShipmentList("sales_order_shipment.list"),
    SalesOrderShipmentInfo("sales_order_shipment.info"),
    SalesOrderShipmentCreate("sales_order_shipment.create"),
    SalesOrderShipmentAddComment("sales_order_shipment.addComment"),
    SalesOrderShipmentAddTrack("sales_order_shipment.addTrack"),
    SalesOrderShipmentRemoveTrack("sales_order_shipment.removeTrack"),
    SalesOrderShipmentGetCarriers("sales_order_shipment.getCarriers"),
    SalesOrderShipmentSendInfo("sales_order_shipment.sendInfo"),

    SalesOrderInvoiceList("sales_order_invoice.list"),
    SalesOrderInvoiceInfo("sales_order_invoice.info"),
    SalesOrderInvoiceCreate("sales_order_invoice.create"),
    SalesOrderInvoiceAddComment("sales_order_invoice.addComment"),
    SalesOrderInvoiceCapture("sales_order_invoice.capture"),
    SalesOrderInvoiceVoid("sales_order_invoice.void"),
    SalesOrderInvoiceCancel("sales_order_invoice.cancel"),

    RegsitryAddItem("list_item.create"),
    RegsitryUpdateItem("list_item.update"),
    RegsitryRemoveItem("list_item.remove"),

    CountryList("country.list"),
    RegionList("region.list"),
    ShoppingCartCreate("cart.create"),
    ShoppingCartLicenseAgreement("cart.license"),
    ShoppingCartTotals("cart.totals"),
    ShoppingCartInfo("cart.info"),
    ShoppingCartOrder("cart.order"),
    ShoppingCartCustomerSet("cart_customer.set"),
    ShoppingCartCustomerAddresses("cart_customer.addresses"),
    ShoppingCartProductAdd("cart_product.add");

    private String path;

    ResourcePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
