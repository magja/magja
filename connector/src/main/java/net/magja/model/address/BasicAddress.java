/**
 * @author andre
 *
 */
package net.magja.model.address;

import java.util.Map;

@SuppressWarnings("serial")
public class BasicAddress extends Address<Map<String, Object>> {

	@Override
	public Map<String, Object> serializeToApi() {
		Map<String, Object> props = getAllProperties();
		return props;
	}

}
