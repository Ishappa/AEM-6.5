package com.adobe.aem.guides.wknd.core.services;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Component(service = DemoService.class,immediate = true)
public class DemoServiceImpl implements DemoService {
    private static final Logger LOG= LoggerFactory.getLogger(DemoServiceImpl.class);

    @SlingObject
    SlingHttpServletRequest request;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private SlingRepository slingRepository;

    @Activate
    public void activate(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE ACTIVATE================");
        LOG.info("\n {} = {} ",componentContext.getBundleContext().getBundle().getBundleId(),componentContext.getBundleContext().getBundle().getSymbolicName());
    }

    @Deactivate
    public void deactivate(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE DEACTIVATE================");
    }

    @Modified
    public void modified(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE MODIFIED================");
    }

    @Override
    public Iterator<Page> getPages(){

//            ResourceResolver resourceResolver= ResolverWknd.newResolver(resourceResolverFactory);
            ResourceResolver resourceResolver=request.getResourceResolver();
            PageManager pageManager=resourceResolver.adaptTo(PageManager.class);
            Page page=pageManager.getPage("/content/wknd/us");
            Iterator<Page> pages=page.listChildren();
            return pages;


    }

}