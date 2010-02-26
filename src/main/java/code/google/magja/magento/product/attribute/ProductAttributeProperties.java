/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */
package code.google.magja.magento.product.attribute;

import java.util.Map;

import code.google.magja.magento.Properties;


public class ProductAttributeProperties extends Properties {

	/*
	 * Constructor
	 */
	public ProductAttributeProperties() {
	}

	public void createText(String label) {
		setGroup("General");
		// setType("");
		setBackend("");
		setFrontend("");
		setLabel(label);
		setInput("text");
		setClass("");
		setSource("");
		setGlobal(2);
		setVisible(1);
		setRequired(0);
		setUser_defined(1);
		setDefault("");
		setSearchable(1);
		setFilterable(1);
		setComparable(1);
		setVisible_on_front(1);
		setVisible_in_advanced_search(1);
		setUnique(0);
	}

	public void createSelect(String label) {
		setGroup("General");
		setType("int");
		setBackend("");
		setFrontend("");
		setLabel(label);
		setInput("select");
		setClass("");
		setSource("");
		setGlobal(2);
		setVisible(1);
		setRequired(0);
		setUser_defined(1);
		setDefault("");
		setSearchable(1);
		setFilterable(1);
		setComparable(1);
		setVisible_on_front(0);
		setVisible_in_advanced_search(1);
		setUnique(0);
		// setApply_to(new String[]{"all"});
	}

	/*
	 * Setter
	 */
	public void setGroup(String value) {
		set("group", value);
	}

	public void setType(String value) {
		set("type", value);
	}

	public void setBackend(String value) {
		set("backend", value);
	}

	public void setFrontend(String value) {
		set("frontend", value);
	}

	public void setLabel(String value) {
		set("label", value);
	}

	/**
	 * @param value
	 *            - Catalog Input Type for Store Owner
	 *
	 *            <p>
	 *            <strong>Possible values</strong><br>
	 *            text - Text Field<br>
	 *            textarea - Text Area<br>
	 *            date - Date<br>
	 *            boolean - Yes/No<br>
	 *            multiselect - Multiple Select<br>
	 *            select - Dropdown<br>
	 *            price - Price<br>
	 *            media_image - Media Image<br>
	 *            weee - Fixed Product Tax<br>
	 *            </p>
	 */
	public void setInput(String value) {
		set("input", value);
	}

	public void setClass(String value) {
		set("class", value);
	}

	public void setSource(String value) {
		set("source", value);
	}

	public void setDefault(String value) {
		set("default", value);
	}

	public void setGlobal(int value) {
		set("global", value);
	}

	public void setVisible(int value) {
		set("visible", value);
	}

	public void setRequired(int value) {
		set("required", value);
	}

	public void setUser_defined(int value) {
		set("user_defined", value);
	}

	public void setSearchable(int value) {
		set("searchable", value);
	}

	public void setFilterable(int value) {
		set("filterable", value);
	}

	public void setComparable(int value) {
		set("comparable", value);
	}

	public void setVisible_on_front(int value) {
		set("visible_on_front", value);
	}

	public void setVisible_in_advanced_search(int value) {
		set("visible_in_advanced_search", value);
	}

	public void setUnique(int value) {
		set("unique", value);
	}

	/**
	 * values: simple,configurable,virtual
	 */
	public void setApply_to(String[] value) {
		set("apply_to", value);
	}

	public void setOption(Map<String, Object> value) {
		set("option", value);
	}
}
