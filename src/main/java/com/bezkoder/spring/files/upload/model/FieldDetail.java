package com.bezkoder.spring.files.upload.model;

public class FieldDetail {
	private String id;
	private String key;
	private String code;
	private String value;

	public FieldDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldDetail(String id, String key, String code, String value) {
		super();
		this.id = id;
		this.key = key;
		this.code = code;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FieldDetail [id=" + id + ", key=" + key + ", code=" + code + ", value=" + value + "]";
	}

}
