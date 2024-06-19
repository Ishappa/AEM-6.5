package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SubscribeItems {

    @ValueMapValue
    private String title;

    @ChildResource
    private List<SubscribeItems> sub;

    public List<SubscribeItems> getSub() {
        return sub;
    }

    public String getTitle() {
        return title;
    }
}
