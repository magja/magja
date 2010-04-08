/**
 *
 */
package com.google.code.magja.service.region;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.region.Region;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class RegionRemoteServiceImpl extends GeneralServiceImpl<Region> implements RegionRemoteService {

	/* (non-Javadoc)
	 * @see com.google.code.magja.service.region.RegionRemoteService#list(java.lang.String)
	 */
	@Override
	public List<Region> list(String countryId) throws ServiceException {
		List<Region> regions = new ArrayList<Region>();

		List<Map<String, Object>> remote_list = null;
		try {
			remote_list = (List<Map<String, Object>>) soapClient.call(ResourcePath.RegionList, countryId);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if(remote_list == null) return regions;

		for (Map<String, Object> map : remote_list) {

			Region region = new Region();

			for (Map.Entry<String, Object> attr : map.entrySet())
				region.set(attr.getKey(), attr.getValue());

			regions.add(region);
		}

		return regions;
	}



}
