package com.gqhmt.sys.beans;

public class SysPara {
	private long id;
	private String key;
    private String value;
    private String description;



	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
