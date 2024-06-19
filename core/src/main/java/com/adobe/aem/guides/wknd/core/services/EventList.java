package com.adobe.aem.guides.wknd.core.services;

import org.apache.sling.api.resource.ResourceResolver;
import java.util.List;
import java.util.Map;

public interface EventList {
    List<Map<String, Object>> pagelist(int UserEnter,ResourceResolver resolver);


}
