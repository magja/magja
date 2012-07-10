package com.google.code.magja.model.product;

import java.io.Serializable;

/**
 * @author atang
 *
 */
@SuppressWarnings("serial")
public class ProductRefMagja implements Serializable {
	private String id;
	private String kind;
	private String slug;
	private String name;
	private String photoId;
	private String shopId;
	
	public ProductRefMagja() {
		super();
	}
	
	public ProductRefMagja(String id, String slug, String name,
			String photoId, String shopId) {
		super();
		this.id = id;
		this.kind = "Product";
		this.slug = slug;
		this.name = name;
		this.photoId = photoId;
		this.shopId = shopId;
	}
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "ProductRefMagja [id=" + id + ", slug=" + slug + ", name="
				+ name + ", photoId=" + photoId + ", shopId=" + shopId + "]";
	}

}
