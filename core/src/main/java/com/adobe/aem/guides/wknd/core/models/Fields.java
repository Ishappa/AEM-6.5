package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Fields {

    @ValueMapValue
    private String section;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String external;

    @ValueMapValue
    private String internal;


    public String getSection() {
        return section;
    }

    public String getLink() {
        return link;
    }

    public String getExternal() {
        return external;
    }

    public String getInternal() {
        return internal;
    }
}
