/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

package Magento.Category;

import Magento.Properties;

public class CategoryProperties extends Properties {

	/*
	 * Constructor
	 */
	public CategoryProperties() {
	}

	/*
	 * Constructor with minimal properties
	 */
	public CategoryProperties(String categoryName) {
		setName(categoryName);
		setAvailable_sort_by("position");
		setDefault_sort_by("position");
		setIs_active(1);
		setIs_anchor(1);
	}

	/*
	 * Getter
	 */
	public String getName() {
		return (String) get("name");
	}

	/*
	 * Setter
	 */
	public void setAvailable_sort_by(String value) {
		set("available_sort_by", value);
	}

	public void setDefault_sort_by(String value) {
		set("default_sort_by", value);
	}

	public void setDescription(String value) {
		set("description", value);
	}

	public void setIs_active(int value) {
		set("is_active", value);
	}

	public void setMeta_description(String value) {
		set("meta_description", value);
	}

	public void setMeta_keywords(String value) {
		set("meta_keywords", value);
	}

	public void setName(String value) {
		set("name", value);
	}

	public void setPosition(int value) {
		set("position", value);
	}

	public void setIs_anchor(int value) {
		set("is_anchor", value);
	}
}
