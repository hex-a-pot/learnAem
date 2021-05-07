package com.adobe.aem.guides.learnAEM.core.models;

//import com.adobe.aem.guides.learnAEM.core.models.Author;

import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
//import org.osgi.service.log.Logger;
//import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
//import org.apache.sling.models.annotations.injectorspecific.Self;
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.annotation.PostConstruct;
//import org.apache.sling.api.resource.Resource;
import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = SlingHttpServletRequest.class,
		adapters =  Author.class,
		defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL
		)
public class AuthorImpl implements Author {
	private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);
	
	@SlingObject
	ResourceResolver resourceResolver;
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@Via("resource")
	@Inject
	@Default(values="default First Name")
	String firstName;
	
	@ResourcePath(path = "/content/learnAEM/en/second")
	@Via("resource")
	Resource resource;
	
	@Inject
	@Via("resource")
	@Named("jcr:lastModifiedBy")
	String modifiedBy;
	
	
	@RequestAttribute(name = "rAttribute")
	String reqAttribute;
	
	
	@Via("resource")
	@ValueMapValue
	@Default(values="default Last Name")
	String lastName;
	
	
	@ScriptVariable
	Page currentPage;
//	@Inject
//	boolean professor;

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	@Override
	public String getPageTitle() {
		// TODO Auto-generated method stub
		return currentPage.getTitle();
	}

	@Override
	public String getRequestAttribute() {
		// TODO Auto-generated method stub
		return reqAttribute;
	}

	@Override
	public String getHomePage() {
		// TODO Auto-generated method stub
		return resource.getName();
	}
	
	@PostConstruct
	protected void init()
	{
		LOG.info("\n INSIDE INIT {} : {} ",currentPage.getTitle(),resource.getPath());
	}

	@Override
	public String getModifiedBy() {
		// TODO Auto-generated method stub
		return modifiedBy;
	}
	

}
