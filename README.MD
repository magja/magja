# Magja | Magento Java Connector
[![Build Status](https://travis-ci.org/magja/magja.svg?branch=master)](https://travis-ci.org/magja/magja)
[![codecov](https://codecov.io/gh/magja/magja/branch/master/graph/badge.svg)](https://codecov.io/gh/magja/magja)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.magja/magja/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.magja/magja)

Magja is a Java Connector for Magento's SOAP API that allows easy integration with the popular shop system
and allows to exchange all data available by the Magento API.

## Core Features
* Basic support for **Magento 1.x SOAP API V1**
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

## Documentation

The official documentation is located on the [Magja Homepage](http://magja.net/).

## Development notes

We use Travis CI to ensure that builds are passing. CodeCov is used to measure test coverage and SonarQube to find bugs.

Magja is build using Apache Maven. Please run:

    mvn clean package

to build the software. There is a Maven profile included, running the entire integration-test suite against a Magento Shop.
Please make sure to place your `magento-api.properties` file in `src/test/resources` and run the following command to execute the integration tests:

    mvn clean install -P itest

Fo producing a release, the following command is used (only if you have enough permissions to do so):

    mvn --batch-mode -Dmaven.test.skip=true -Dresume=false clean release:prepare release:perform

### Sonar metrics

[![Quality Gate](https://sonarqube.com/api/badges/gate?key=net.magja:magja)](https://sonarcloud.io/dashboard?id=net.magja%3Amagja)
[![Sonar Rating](https://sonarqube.com/api/badges/measure?key=net.magja:magja&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=net.magja%3Amagja)
[![Sonar Debt Ration](https://sonarqube.com/api/badges/measure?key=net.magja:magja&metric=sqale_debt_ratio)](https://sonarcloud.io/dashboard?id=net.magja%3Amagja)
[![Sonar Reliability](https://sonarqube.com/api/badges/measure?key=net.magja:magja&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=net.magja%3Amagja)

## References and History

Magja has been developed on Google Code, so old resources are still available at:

* Magja at google code: http://code.google.com/p/magja/
* Installation for use: http://code.google.com/p/magja/wiki/Installation
* Setup project for development: http://code.google.com/p/magja/wiki/SetupProject
* Wiki Pages: http://code.google.com/p/magja/w/list
