package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Formnew {
    @ValueMapValue
    @Default(values = "naveen")
    private String titles;
    @ValueMapValue
    private String email;

    @ValueMapValue
    private String pass;

    @ValueMapValue

    private String fpass;

    @ValueMapValue
    private String buttons;

    @ValueMapValue
    private String signup;

    public String getTitles() {
        return titles;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getFpass() {
        return fpass;
    }

    public String getButtons() {
        return buttons;
    }

    public String getSignup() {
        return signup;
    }
}
