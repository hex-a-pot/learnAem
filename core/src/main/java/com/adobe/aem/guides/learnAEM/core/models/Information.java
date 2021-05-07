package com.adobe.aem.guides.learnAEM.core.models;

import java.util.List;
import java.util.Map;

public interface Information {
	public String getName();
	public String getAge();
	public String getAddress();
	public String getPath();
//	public String getAttribute();
	public String getOsgiServiceMessage();
	public int getOsgiConfigLength();
	public int getOsgiConfigBreadth();
	public String[] getBooksArray();
	public List<String> getBooks();
	public Map<String,String> getBooksMap();
	public List<Map<String,String>> getBookDetailsWithMap();
}
