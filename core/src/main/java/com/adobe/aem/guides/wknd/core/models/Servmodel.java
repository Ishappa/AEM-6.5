package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.services.ModInter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Servmodel {


//    @OSGiService(filter = "(component.name=com.adobe.aem.guides.wknd.core.services.ModInterImpl)")
//    @OSGiService
    @OSGiService(filter = "(component.name=one)")
    private ModInter msg;

    public String getMsg() {
        return msg.message();
    }
}
