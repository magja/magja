/**
 *
 */
package code.google.magja.service.category;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;
import code.google.magja.model.category.CategoryAttribute;
import code.google.magja.model.category.CategoryAttributeOption;
import code.google.magja.service.GeneralServiceImpl;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class CategoryAttributeRemoteServiceImpl extends
		GeneralServiceImpl<CategoryAttribute> implements
		CategoryAttributeRemoteService {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * code.google.magja.service.category.CategoryAttributeRemoteService#listAll
	 * (java.lang.String)
	 */
	@Override
	public List<CategoryAttribute> listAll(String storeView)
			throws ServiceException {

		List<CategoryAttribute> results = new ArrayList<CategoryAttribute>();

		List<Map<String, Object>> attributes = null;
		try {
			attributes = (List<Map<String, Object>>) soapClient.call(
					ResourcePath.CategoryAttributeList, "");
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if (attributes == null)
			return results;

		for (Map<String, Object> att : attributes) {

			CategoryAttribute attribute = new CategoryAttribute();

			for (Map.Entry<String, Object> attr : att.entrySet())
				attribute.set(attr.getKey(), attr.getValue());

			// verify options
			if (att.get("type") != null) {
				String type = (String) att.get("type");
				if (type.equals("select") || type.equals("multiselect")) {

					List<Object> optParamList = new LinkedList<Object>();
					optParamList.add(att.get("attribute_id"));
					optParamList.add(storeView);

					List<Map<String, Object>> optList = null;
					try {
						optList = (List<Map<String, Object>>) soapClient.call(
								ResourcePath.CategoryAttributeOptions,
								optParamList);
					} catch (AxisFault e) {
						e.printStackTrace();
						throw new ServiceException(e.getMessage());
					}

					if (optList != null) {
						for (Map<String, Object> optAtt : optList) {

							CategoryAttributeOption option = new CategoryAttributeOption();
							option.setLabel((String) optAtt.get("label"));
							option.setValue(optAtt.get("value"));

							attribute.getOptions().add(option);
						}
					}
				}
			}

			results.add(attribute);
		}

		return results;
	}

}
