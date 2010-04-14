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
 * http://www.magentocommerce.com/support/magento_core_api
 */
package com.google.code.magja.magento;

import com.google.code.magja.soap.MagentoSoapClient;
import com.google.code.magja.soap.SoapClient;

public class Connection {

    protected SoapClient client = null;

    public Connection() {
        client = MagentoSoapClient.getInstance();
    }
}
