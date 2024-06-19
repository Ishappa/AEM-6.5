package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Store Api Key" ,description = "Store multiple Api Key")
public @interface ApiMultiConfig {
    @AttributeDefinition(
            name = "Api Key",
            description = "Add Multiple Api Key",
            type = AttributeType.STRING
    )
    public String storeKey();
}
