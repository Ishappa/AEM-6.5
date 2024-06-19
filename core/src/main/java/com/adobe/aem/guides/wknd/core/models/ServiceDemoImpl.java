package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.services.DemoService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ServiceDemo.class ,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl implements ServiceDemo {
    private static final Logger LOG= LoggerFactory.getLogger(ServiceDemoImpl.class);

    @ValueMapValue
    private String text;
    @OSGiService
    DemoService demoService;
    @Override
    public Iterator<Page> getPageslist(){
        return demoService.getPages();
    }

    @Override
    public String getText() {
        return text;
    }


}
