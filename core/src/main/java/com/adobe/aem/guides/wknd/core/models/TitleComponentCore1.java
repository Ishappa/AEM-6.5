package com.adobe.aem.guides.wknd.core.models;

import com.adobe.cq.wcm.core.components.models.Carousel;
import com.adobe.cq.wcm.core.components.models.Title;
import com.day.cq.wcm.api.Page;
import lombok.experimental.Delegate;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.via.ResourceSuperType;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,adapters = Title.class,resourceType = "wknd/components/titleDelegate")
public class TitleComponentCore1 implements Title {

    @Self @Via(type = ResourceSuperType.class)
    @Delegate(excludes = DelegateC.class )
    private Title atitle;

    @ScriptVariable
    Page currentPage;

    public interface DelegateC{
        String getText();
    }

    @Override
    public String getText() {
        return currentPage.getTitle();
    }

}
