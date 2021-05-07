package com.tutorial.services;

import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = FirstService.class, immediate = true)
public class FirstServiceImpl implements FirstService {
	private static final Logger LOG = LoggerFactory.getLogger(FirstServiceImpl.class); 

	@Override
	public String getMesage() {
		// TODO Auto-generated method stub
		return "MY MESSAGE FROM FIRST SERVICE";
	}
	
	@Activate
	public void activate()
	{
		LOG.info("\n------------------------INSIDE ACTIVATE------------------");
		
		
	}
	
	@Deactivate
	public void deactivate()
	{
		LOG.info("\n---------------------INSIDE DEACTIVATE-------------------");
	}
	
}
