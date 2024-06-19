package com.adobe.aem.guides.wknd.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;


@ObjectClassDefinition(name = "Schedular Training" , description = "This is Scheduler Training")
public @interface SchedularConfig {
    @AttributeDefinition(
            name = "Scheduler Name",
            description = "Scheduler Name of Training",
            type = AttributeType.STRING
    )
    public String schedularname() default "AEM";

    @AttributeDefinition(
            name = "Corn Expression",
            description = "Corn expression",
            type = AttributeType.STRING
    )
    public String cornexpression() default "*/2 * * * *";

    @AttributeDefinition(
            name = "Concurrent",
            description = "Concurrent of Scheduler",
            type = AttributeType.BOOLEAN
    )
    public boolean concurrent() default true;

}
