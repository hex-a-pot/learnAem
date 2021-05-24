package com.adobe.aem.guides.learnAEM.core.servlets;

import java.io.IOException;


import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
		 resourceTypes = "learnAEM/components/structure/page")
public class LearnAemResourceTypeServlet extends SlingAllMethodsServlet {
	private static final Logger LOG = LoggerFactory.getLogger(LearnAemResourceTypeServlet.class);
	
	@Override
	protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException{
		final Resource resource = req.getResource();
		resp.setContentType("text/plain");
		resp.getWriter().write("Page title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
	}

}
