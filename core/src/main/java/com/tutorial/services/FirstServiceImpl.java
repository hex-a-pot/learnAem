package com.tutorial.services;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;
import com.tutorial.utilities.ResolverUtil;

@Component(service = FirstService.class, immediate = true)
public class FirstServiceImpl implements FirstService {
	private static final Logger LOG = LoggerFactory.getLogger(FirstServiceImpl.class); 
	
	@Reference
	private ResourceResolverFactory resourceResolverFactory;

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

	@Override
	public Map<String,String> getProperties() throws LoginException {
		// TODO Auto-generated method stub
		Map<String,String> pageProperties = new HashMap<String,String>();
		ResourceResolver resolver = null;
		resolver = ResolverUtil.newResolver(resourceResolverFactory);
		Resource pageResource = resolver.getResource("/content/learnAEM");
		Page page = pageResource.adaptTo(Page.class);
		pageProperties.put("Title",page.getTitle());
		pageProperties.put("Path",page.getPath());
		pageProperties.put("Vanity URL",page.getVanityUrl());
		return pageProperties;

	}

	@Override
	public List<String> getCsvDetails() throws LoginException {
		List<String> csvDetails = new ArrayList<String>();
		ResourceResolver resolver = null;
		resolver = ResolverUtil.newResolver(resourceResolverFactory);
		Resource csvResource = resolver.getResource("/content/dam/learnAEM/English/Csv/username.csv");
		Asset asset = csvResource.adaptTo(Asset.class);
		Rendition rendition = asset.getOriginal();
		InputStream inputStream = rendition.adaptTo(InputStream.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		
		try {
			while((line = br.readLine()) != null)
			{
				String[] tempArr;
				tempArr = line.split(";");
				String temp = "";
				for(String str : tempArr)
				{
					temp += str+" "; 
				}

				csvDetails.add(temp);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvDetails;
	}
	
	
}
