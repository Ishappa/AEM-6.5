package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        resourceType = "wknd/components/training",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson" , extensions = "json")
public class Training1 {

    @ValueMapValue
    private String name;

    @ValueMapValue
    @Default(values = "Manager")
    private String role;

    @ValueMapValue
    private String place;

    @ValueMapValue
    private int exp;


    private String type;

    public String getType() {
        return type;
    }
    private String pageTitle;

    public String getPageTitle() {
        return pageTitle;
    }
    @ScriptVariable                   //    It is used to fetch global objects
    private ValueMap pageProperties;

    @PostConstruct
    public void init(){
        pageTitle= pageProperties.get("jcr:title", String.class);
        type = pageProperties.get("jcr:primaryType", String.class);
    }

//    private String slingresourceType;
//
//    @PostConstruct
//    public void type(){
//        slingresourceType=pageProperties.get("sling:resourceType", String.class);
//    }

    @ValueMapValue
    @Named("sling:resourceType")
    private String slingresourceType;


    public String getSlingresourceType() {
        return slingresourceType;
    }
    @Self
    ResourceResolver resolver;

    @Path("/content/wknd/us/en")
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public int getExp() {
        return exp;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public String getRole() {
        return role;
    }

    public String getPlace() {
        return place;
    }



//    @Self
//    SlingHttpServletRequest request;
//
//
    @RequestAttribute
    String training;

    public String getTraining() {
        return training;
    }

}
