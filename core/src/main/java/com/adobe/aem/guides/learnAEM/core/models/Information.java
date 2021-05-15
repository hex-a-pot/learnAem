package com.adobe.aem.guides.learnAEM.core.models;

import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;

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
	public Map<String,String> getResolveResource() throws LoginException;
	public List<String> getCsvDetails() throws LoginException;
}
