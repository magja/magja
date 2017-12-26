# Magja | Magento Java Connector
[![Build Status](https://travis-ci.org/magja/magja.svg?branch=master)](https://travis-ci.org/magja/magja)
[![codecov](https://codecov.io/gh/magja/magja/branch/master/graph/badge.svg)](https://codecov.io/gh/magja/magja)


Magja is a Java Connector for Magento's SOAP API that allows easy integration with the popular shop system 
and allows to exchange all data available by the Magento API.

## Core Features
* Basic support for Magento 1.x SOAP API V1
* Allows access to:
  * Product
  * Product Media
  * Product Link
  * Product Categories
  * Product Attributes
  * Country
  * Region
  * Customer
  * Order
  * Invoice
  * Cart
* Extensible (without code generation) for custom API

## Installation and configuration
 
Magja artifacts are available in the Maven Central repository. Pleas put the following repository definition into your `pom.xml`:

    <dependency>
      <groupId>net.magja.magja</groupId>
      <artifactId>magja</artifactId>
      <version>1.1.0-SNAPSHOT</version>
    </dependency>
    
to get started. In order to connect to your Magento Shop installation, you need to put `magento-api.properties` on your classpath.
You can copy an example properties from samples folder. Essentially, the properties are self-explanatory:
       
    # SOAP XML/RPC user
    magento-api-username=<replace with user> 
    
    # SOAP XML/RPC api key
    magento-api-password=<replace with api key>
    
    # URL of the Magento installation
    # If your shop is installed not using a prefix e.g. /magento
    # please add the prefix to the path:
    # http://<yourmagento-host>/magento/index.php/api/soap/
    magento-api-url=http://<yourmagento-host>/index.php/api/soap/
    
    # the ID of the default attribute set
    default-attribute-set-id=4
 
    # the ID of the default root category
    default-root-category-id=2

If you want to use the client behind a HTTP proxy, you can specify the settings in `magento-api.properties`:

    # HTTP proxy settings, default is false
    http-proxy-enabled=true
    http-proxy-host=localhost
    http-proxy-port=8080
    
    # HTTP proxy auth settings, default is false
    http-proxy-auth-enabled=true
    http-proxy-username=user
    http-proxy-password=pass

If your shop is protected with HTTP Basic Authentication, you can pass a username and password using the following properties:

    # HTTP auth settings, default is false
    http-auth-enabled=true
    http-username=user
    http-password=pass


## Basic usage

Here is a sample usage of the product service:

    Logger log = LoggerFactory.getLogger("logger");
    RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    ProductRemoteService productService = remoteServiceFactory.getProductRemoteService();

    Product product = new Product();
    product.setSku("0001-QWER-12090");
    product.setName("Milk");

    try {
      productService.add(product);
      
      List<Product> allProducts = productService.listAll();
      for (Product shopProduct : allProducts) {
        log.info("{}", shopProduct);;
      }
      
    } catch (ServiceException e) {
      log.error("Error manipulating products", e);
    } catch (NoSuchAlgorithmException e) {
      log.error("Error manipulating products", e);
    }

## More examples

### Category

Get only the basic information of a Product Category, without its dependencies:

    Category category = remoteServiceFactory.getCategoryRemoteService.getByIdClean(2);

Get Product Category with its children:

    Category category = remoteServiceFactory.getCategoryRemoteService.getByIdWithChildren(2);
    for (Category child : category.getChildren()) {
      // do something with the child
    }

Get Product Category with its parent:

    Category category = remoteServiceFactory.getCategoryRemoteService.getByIdWithParent(2);
    Category parent = category.getParent();
    
Get category with its parent and children:

    Category category = remoteServiceFactory.getCategoryRemoteService.getByIdWithParentAndChildren(2)


### Product

List all products with dependencies (slower)

    List<Product> products = remoteServiceFactory.getProductRemoteService.listAll();

List all products without dependencies (faster)

    List<Product> products = remoteServiceFactory.getProductRemoteService.listAllNoDep()

Create Product

    Product product = new Product();
    product.setSku("DUMMYPRD");
    product.setName("Lovely Umbrella");
    product.setShortDescription("This is a short description");
    product.setDescription("This is a description for Product");
    product.setPrice(250.99);
    product.setCost(100.22);
    product.setEnabled(true);
    product.setWeight(0.500);
    product.setType(ProductTypeEnum.SIMPLE.getProductType());
    product.setAttributeSet(new ProductAttributeSet(4, "Default"));
    
    // category
    List<Category> categories = new ArrayList<Category>();
    categories.add(new Category(2));
    product.setCategories(categories);


    product.setWebsites(new Integer[] { 1 });
    product.setVisibility(Visibility.CATALOG_SEARCH);

    // inventory
    product.setQty(new Double(20));
    product.setInStock(true);
    product.setMetaTitle("meta title");
    product.setMetaDescription("meta description");
    product.setMetaKeyword("keyword");

    // Optional: you can set the properties directly by-passing the setter like too:
    product.set("meta_description", "one two tree")

    // then, we just instantiate the service to persist the product
    remoteServiceFactory.getProductRemoteService.save(product)

### Customer

Reading the Customers

    List<Customer> customers = remoteServiceFactory.customerRemoteService.list();

## Development notes

We use Travis CI to ensure that builds are passing. CodeCov is used to measure test coverage and SonarQube to find bugs.  

Magja is build using Apache Maven. Please run:
     
    mvn clean package 
    
to build the software. There is a Maven profile included, running the entire integration-test suite against a Magento Shop. 
Please make sure to place your `magento-api.properties` file in `src/test/resources` and run the following command to execute the integration tests:

    mvn clean install -P itest
    
Fo producing a release, the following command is used:
    
    mvn -Dmaven.test.skip=true -Dresume=false clean javadoc:jar source:jar release:prepare release:perform
    

## References and History

Magja has been developed on Google Code, so old resources are still available at:

* Magja: http://code.google.com/p/magja/
* Installation for Use: http://code.google.com/p/magja/wiki/Installation
* Setup Project for Development: http://code.google.com/p/magja/wiki/SetupProject
* Wiki Pages: http://code.google.com/p/magja/w/list 

## Acknowledgments

Bandung Everlasting Beauty logo downloaded from Info Bandung (http://infobandung.codeplex.com/) CodePlex project designed by Achmad Sutarjono.
