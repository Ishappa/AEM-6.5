package com.adobe.aem.guides.wknd.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.engine.SlingRequestProcessor;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.servlethelpers.internalrequests.SlingInternalRequest;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SingleImageUpload {
    @ValueMapValue
    private String path;

    @ValueMapValue
    private String name;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    private String responString;

    public String getResponString() {
        return responString;
    }

    @SlingObject
    private ResourceResolver resourceResolver;

    @OSGiService
    private SlingRequestProcessor slingRequestProcessor;

    @PostConstruct
    private void init() {
        try {

             responString = StringUtils.EMPTY;
            SlingInternalRequest slingInternalRequest = new SlingInternalRequest(resourceResolver, slingRequestProcessor, "/bin/SingleImageUploadServlet");
            slingInternalRequest.withParameter("src",path);
            slingInternalRequest.withParameter("imgName",name);


            responString = slingInternalRequest.withRequestMethod("GET")
                    .execute()
                    .checkStatus(200)
                    .checkResponseContentType("text/plain")
                    .getResponseAsString();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
