package com.adobe.aem.guides.learnAEM.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = Resource.class,
adapters =  Resume.class,
defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL
)
public class ResumeImpl implements Resume {
	private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);

	@PostConstruct
	public void init()
	{
		String phoneList = "";
		for(String phone : phones)
		{
			phoneList += phone+"\n";
		}
		LOG.info("Name = "+firstName+" AGE= "+age+"Gender = "+gender+"phones = \n"+phoneList);
	}

	@Inject
	String firstName;
	
	@Inject
	int age;
	
	@Inject
	String gender;
	
	@ValueMapValue
	private List<String> phones;
	
//	@ValueMapValue
//	private boolean isAuthor;
	
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return age;
	}

	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return gender;
	}

	@Override
	public List<String> getPhoneNumber() {
		// TODO Auto-generated method stub
		if (phones != null)
			return new ArrayList<String>(phones);
		else
			return Collections.emptyList();
			
	}

//	@Override
//	public boolean getCheckAuthor() {
//		// TODO Auto-generated method stub
//		return isAuthor;
//	}
	

	

}
