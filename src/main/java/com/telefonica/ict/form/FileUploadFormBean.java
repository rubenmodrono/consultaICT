package com.telefonica.ict.form;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"bytes"})
public class FileUploadFormBean {

	 private String name;
	 private String size;
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

	
	 

}
