package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.services.DemoInterface;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServModel2 {


    @OSGiService
    DemoInterface demo;

    private String name;

    private String loc;


    public String getName() {
        return demo.ename();
    }

    public String getLoc() {
        return demo.location();
    }
}
