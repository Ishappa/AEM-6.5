package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.RepositoryException;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class  Ish_project1 {

    @ValueMapValue
    private String fname;

    public String getFname() {
        return fname;
    }

    @ValueMapValue
    @Default(values = "AEM Developer")
    private String desig;


    public String getDesig() {
        return desig.toUpperCase();
    }

    @ValueMapValue
    private String loc;

    public String getLoc() {
        return loc;
    }

    @ValueMapValue
    private int exp;

    public int getExp() {
        return exp;
    }



    @ValueMapValue
    @Named("sling:resourceType")
    private String slingresourceType;

    public String getSlingresourceType() {
        return slingresourceType;
    }

    @Inject
    private String pageTitle;

    public String getPageTitle() {
        return pageTitle;
    }

    @Inject
    private String pageInfo;

    public String getPageInfo() {
        return pageInfo;
    }
    @Inject
    private Resource resource;

    public String getResource() {
        return resource.getPath();
    }


    @ScriptVariable
    private Page currentPage;

    @ScriptVariable
    private ValueMap pageProperties;

    @PostConstruct
    public void init() throws RepositoryException {

        pageTitle = pageProperties.get("jcr:title", String.class);
        pageInfo = currentPage.getPath();

    }

    @RequestAttribute
    private String Ish_project;

    public String getIsh_project() {
        return Ish_project;
    }



    @ResourcePath(path = "/apps/wknd/components/Ish_project1")
    private Resource jcrtitle;

    public String getJcrtitle() {
        return jcrtitle.getValueMap().get("jcr:title",String.class);
    }
}
