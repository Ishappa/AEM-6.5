package com.adobe.aem.guides.wknd.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Schedular-ish", description = "This is the Ish config")
public @interface Demo1SchedularConfig {

    @AttributeDefinition(
            name = "Name",
            description = "Enter the name of the schedular config",
            type = AttributeType.STRING
    )
    public String schedularName() default "ish-Schedular";

    @AttributeDefinition(
            name = "Crone Expression",
            description = "Enter the crone expression",
            type = AttributeType.STRING
    )
    public String cronexp() default "* * * * *";

    @AttributeDefinition(
            name = "Concurrent",
            description = "concurrent of demo schedular",
            type = AttributeType.BOOLEAN
    )
    public boolean concurrent() default true;

}
