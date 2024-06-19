package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.services.Demo1Interface;
import com.adobe.aem.guides.wknd.core.services.DemoInterface;
import com.adobe.aem.guides.wknd.core.services.WeatherService;
import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Model(adaptables = {Resource.class,SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class First  {
    @ValueMapValue
    private String text;

    public String getText() {
        return text;
    }


    @ScriptVariable
    private Page currentPage;

    @Inject
    private String pageTitle;

    public String getPageTitle() {
        if (currentPage != null) {
            return currentPage.getTitle();
        }
        return "Page Not Found";
    }

    @ValueMapValue
    @Named("sling:resourceType")
    private String resourceType;

    public String getResourceType() {
        return resourceType;
    }

//    @Inject
//    @Source(value = "script:binding")
//    private Resource resource;




    @OSGiService
    DemoInterface demo;

    private String fname;

    private String location;

    public String getFname() {
        return demo.ename();
    }

    public String getLocation() {
        return demo.location();
    }



//    @OSGiService
//    Demo1Interface demo;
//
//    private String name;
//
//    public String getName() {
//        return demo.name();
//    }
//    private String locat;
//
//    public List getLocat() {
//        return demo.instance();
//    }
}


