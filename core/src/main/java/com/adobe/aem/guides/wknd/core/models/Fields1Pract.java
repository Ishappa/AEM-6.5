package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Fields1Pract {

    @ChildResource
    private List<Fields2pract> fields2;

    public List<Fields2pract> getFields2() {
        return fields2;
    }

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String desc;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
