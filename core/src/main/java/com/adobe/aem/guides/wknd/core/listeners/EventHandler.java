package com.adobe.aem.guides.wknd.core.listeners;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component(immediate = true,service = ResourceChangeListener.class,
property = {
        ResourceChangeListener.PATHS+"=/content/wknd/us/en",
        ResourceChangeListener.CHANGES+"=ADDED",
        ResourceChangeListener.CHANGES+"=REMOVED",
        ResourceChangeListener.CHANGES+"=CHANGED"
})

public class EventHandler implements ResourceChangeListener {

    private static final Logger LOG =LoggerFactory.getLogger(EventHandler.class);
    @Override
    public void onChange(List<ResourceChange> list) {
        for (ResourceChange rc : list){
            LOG.info("\n Event : {}, Resource 12: {}", rc.getType(),rc.getPath());
        }
    }
}
