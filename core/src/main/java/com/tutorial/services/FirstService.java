package com.tutorial.services;

import org.apache.sling.api.resource.LoginException;
import java.util.Map;
import java.util.List;
public interface FirstService {
	String getMesage();
	Map<String,String> getProperties() throws LoginException;
	List<String> getCsvDetails() throws LoginException;
	
}
