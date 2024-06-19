package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PractMulti1Fields {

    @ChildResource
    List<PractMulitFields2> fields2;

    public List<PractMulitFields2> getFields2() {
        return fields2;
    }

    @ValueMapValue
    private String text;

    public String getText() {
        return text;
    }
}
