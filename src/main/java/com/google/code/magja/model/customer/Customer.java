/**
 * @author andre
 *
 */
package com.google.code.magja.model.customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.code.magja.magento.Utils;
import com.google.code.magja.model.BaseMagentoModel;

public class Customer extends BaseMagentoModel {

	private static final long serialVersionUID = 7260666195808816927L;

	public enum Gender {
		MALE(1), FEMALE(2);
		private final Integer value;

		private Gender(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	private String prefix;

	private String firstName;

	private String middleName;

	private String lastName;

	private String suffix;

	private String email;

	private String password;

	private String passwordHash;

	private Integer storeId;

	private Integer websiteId;

	private Integer groupId;

	private Gender gender;

	private String createdAt;

	public static Customer fromAttributes(Map<String, Object> attrs) {
		if (attrs.get("customer_id") != null) {
			Customer customer = new Customer();

			customer.setId(new Integer((String) attrs.get("customer_id")));
			if (attrs.get("customer_email") != null) {
				customer.setEmail((String) attrs.get("customer_email"));
			}
			if (attrs.get("customer_prefix") != null) {
				customer.setPrefix((String) attrs.get("customer_prefix"));
			}
			if (attrs.get("customer_firstname") != null) {
				customer.setFirstName((String) attrs.get("customer_firstname"));
			}
			if (attrs.get("customer_middlename") != null) {
				customer.setMiddleName((String) attrs
						.get("customer_middlename"));
			}
			if (attrs.get("customer_lastname") != null) {
				customer.setLastName((String) attrs.get("customer_lastname"));
			}
			if (attrs.get("customer_lastname") != null) {
				customer.setLastName((String) attrs.get("customer_lastname"));
			}
			if (attrs.get("customer_group_id") != null) {
				customer.setGroupId(new Integer((String) attrs
						.get("customer_group_id")));
			}
			if (attrs.get("customer_gender") != null) {
				Integer gender = new Integer(
						(String) attrs.get("customer_gender"));
				customer.setGender(gender.equals(new Integer(1)) ? Gender.MALE
						: Gender.FEMALE);
			}

			return customer;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {

		Map<String, Object> props = getAllProperties();
		props.remove("customer_id");
		if (gender != null)
			props.put("gender", gender.getValue());
		if (password != null)
			props.put("password_hash", Utils.getMd5Hash(password));

		List<Object> params = new LinkedList<Object>();

		// if its a update, put the customer id
		if (id != null)
			params.add(id);

		params.add(props);

		return params;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix
	 *            the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash
	 *            the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * @return the storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the websiteId
	 */
	public Integer getWebsiteId() {
		return websiteId;
	}

	/**
	 * @param websiteId
	 *            the websiteId to set
	 */
	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((passwordHash == null) ? 0 : passwordHash.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		result = prime * result
				+ ((websiteId == null) ? 0 : websiteId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		if (websiteId == null) {
			if (other.websiteId != null)
				return false;
		} else if (!websiteId.equals(other.websiteId))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [createdAt=" + createdAt + ", email=" + email
				+ ", firstName=" + firstName + ", gender=" + gender
				+ ", groupId=" + groupId + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", password=" + password
				+ ", passwordHash=" + passwordHash + ", prefix=" + prefix
				+ ", storeId=" + storeId + ", suffix=" + suffix
				+ ", websiteId=" + websiteId + ", id=" + id + ", properties="
				+ properties + "]";
	}

}
