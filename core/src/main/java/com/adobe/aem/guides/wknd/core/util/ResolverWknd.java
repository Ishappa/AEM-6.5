package com.adobe.aem.guides.wknd.core.util;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;

import java.util.HashMap;
import java.util.Map;

@Component(service = ResolverWknd.class,immediate = true)
public class ResolverWknd {
    public static final String WKND_SERVICE_USER = "wknduser";
    public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
        final Map<String,Object> params= new HashMap<String,Object>();
        params.put(ResourceResolverFactory.SUBSERVICE,WKND_SERVICE_USER);
        return resourceResolverFactory.getServiceResourceResolver(params);
    }
}