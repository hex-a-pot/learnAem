package com.tutorial.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import com.tutorial.utilities.ResolverUtil;

@Component(service = PageService.class, immediate = true)
public class PageServiceImpl implements PageService {

	@Reference
	private ResourceResolverFactory resourceResolverFactory;
	
	private Session session = null;
	private String user = null;

	@Override
	public String createPage(String pageName, List<String> textContent) {
		String toBeAppended = "";
		for(String str : textContent)
		{
			toBeAppended += str + "\n";
		}
		
		
		ResourceResolver resolver = null;
		try {
			resolver = ResolverUtil.newResolver(resourceResolverFactory);
		} catch (LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Resource csvResource = resolver.getResource("/content/dam/learnAEM/English/Csv/PageCreationDetails.csv");
		Asset asset = csvResource.adaptTo(Asset.class);
		Rendition rendition = asset.getOriginal();
		InputStream inputStream = rendition.adaptTo(InputStream.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String[] tempArr = null;
		try {
			while((line = br.readLine()) != null)
			{
				
				tempArr = line.split(";");

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
		
		String pagePath = tempArr[0];
		String templatePath = tempArr[1];
		String pageTitle = tempArr[2];
		Page newPage = null;
		PageManager pageManager = null;
		
		try {

			session = resolver.adaptTo(Session.class);
			pageManager = resolver.adaptTo(PageManager.class);
			newPage = pageManager.create(pagePath, pageName, templatePath, pageTitle);
			if (newPage != null) {

				user = resolver.getUserID();

				Node newNode = newPage.adaptTo(Node.class);
				Node cont = newNode.getNode("jcr:content");
				if (cont != null) {
					Node rootNode = session.getRootNode();
					String path = rootNode.getPath();
					Node parNode = JcrUtils.getNodeIfExists(cont, "par");
					Node imageNode = JcrUtils.getOrCreateByPath(parNode.getPath()+"/image", JcrConstants.NT_UNSTRUCTURED, session);
					Node textNode = JcrUtils.getNodeIfExists(parNode, "text");
					imageNode.setProperty("sling:resourceType", "foundation/components/image");
				    imageNode.setProperty("fileReference", "/content/dam/we-retail-screens/we-retail-instore-logo.png");
				    textNode.setProperty("text", toBeAppended);
				    session.save();

				}
			}
			return pageName;
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

}
