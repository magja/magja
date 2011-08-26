/**
 * @author andre
 *
 */
package com.google.code.magja.model.media;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis2.util.Base64;

public class Media {

	// TODO: create a constuctor with a InputStream parameter to automatically
	// set the data, mime type and name.

	private String name;

	private String mime;

	private byte[] data;

	public Map<String, Object> serializeToApi() {

		Map<String, Object> mp = new HashMap<String, Object>();

		mp.put("content", Base64.encode(data));
		mp.put("mime", mime);
		mp.put("name", name);

		return mp;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mime
	 */
	public String getMime() {
		return mime;
	}

	/**
	 * @param mime
	 *            the mime to set
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((mime == null) ? 0 : mime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (mime == null) {
			if (other.mime != null)
				return false;
		} else if (!mime.equals(other.mime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "Media [mime=" + mime + ", name=" + name + "]";
	}
}
