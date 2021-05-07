package com.tutorial.components;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = FirstConfig.class, immediate = true)
@Designate(ocd = FirstOsgiConfig.class, factory = true)
public class FirstConfigImpl implements FirstConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(FirstConfigImpl.class);
	private String data;
	private FirstOsgiConfig firstOsgiConfig;
	
	@Activate
	public void activate(final FirstOsgiConfig firstOsgiConfig)
	{
		this.data = String.valueOf(firstOsgiConfig.length() + " - "+firstOsgiConfig.breadth());
		LOG.info("THE DATA IS -"+this.data);
		this.firstOsgiConfig = firstOsgiConfig;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return firstOsgiConfig.length();
	}

	@Override
	public int getBreadth() {
		// TODO Auto-generated method stub
		return firstOsgiConfig.breadth();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return firstOsgiConfig.password();
	}
	
	
	

}
