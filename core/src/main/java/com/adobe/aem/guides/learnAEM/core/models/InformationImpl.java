package com.adobe.aem.guides.learnAEM.core.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.granite.rest.utils.Resources;
import com.day.cq.wcm.api.Page;
import com.tutorial.components.FirstConfig;
import com.tutorial.services.FirstService;

@Model(adaptables = {SlingHttpServletRequest.class, Resources.class},
		adapters = Information.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)


public class InformationImpl implements Information {
	
	@OSGiService
	private FirstService firstService;
	
	@OSGiService
	private FirstConfig firstConfig;
	
	@Inject
	@Via("resource")
	@Default(values = "DUMMY")
	String name;
	
	@Inject
	@Via("resource")
	@Default(values = "45")
	String age;
	
	@ValueMapValue
	@Via("resource")
	@Default(values = "INDIA")
	String address;

	@ScriptVariable
	Page currentPage;
	
//	@RequestAttribute(name = "rAttribute")
//	String attribute;
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getAge() {
		// TODO Auto-generated method stub
		return age;
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return currentPage.getPath();
	}

	@Override
	public String getOsgiServiceMessage() {
		// TODO Auto-generated method stub
		return firstService.getMesage();
	}

	@Override
	public int getOsgiConfigLength() {
		return firstConfig.getLength();
	}

	@Override
	public int getOsgiConfigBreadth() {
		return firstConfig.getBreadth();
	}

	@Override
	public String[] getBooksArray() {
		// TODO Auto-generated method
		String[] stringList = {"BOOK A","BOOK B","BOOK c"};
		return stringList;
	}

	@Override
	public List<String> getBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getBooksMap() {
		// TODO Auto-generated method stub
		Map<String,String> bookMap = new HashMap<String,String>();
		bookMap.put("BOOK A","J.ELIOTT");
		bookMap.put("BOOK B","Elon");
		bookMap.put("BOOK C","MARY");
		bookMap.put("BOOK D","PARKER");
		return bookMap;
	}

	@Override
	public List<Map<String, String>> getBookDetailsWithMap() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

//	@Override
//	public String getAttribute() {
//		// TODO Auto-generated method stub
//		return attribute;
//	}
	

}
