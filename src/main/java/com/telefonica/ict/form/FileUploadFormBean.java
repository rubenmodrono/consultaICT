package com.telefonica.ict.form;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"bytes"})
public class FileUploadFormBean {

	 public enum DeleteType {
	       DELETE;
	    }
	    	
	 private String name;
	 private String size;
	 private String url;
	 private String thumbnailURL;
	 private String deleteURL;
	 private DeleteType deleteType;
	 private String type;
	 
	 private byte[] bytes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailURL() {
		return thumbnailURL;
	}

	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}

	public String getDeleteURL() {
		return deleteURL;
	}

	public void setDeleteURL(String deleteURL) {
		this.deleteURL = deleteURL;
	}

	public DeleteType getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(DeleteType deleteType) {
		this.deleteType = deleteType;
	}
	 

}
