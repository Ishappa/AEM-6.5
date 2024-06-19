
package com.adobe.aem.guides.wknd.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


@ObjectClassDefinition(name = "Schedular Demo" , description = "This is SchedulerDemo")
public @interface SchedulerDemoConfig {
    @AttributeDefinition(
            name = "Scheduler Name",
            description = "Scheduler Name of Training",
            type = AttributeType.STRING
    )
    public String schedularname() default "AEM Demo";

    @AttributeDefinition(
            name = "Cron Expression",
            description = "Cron expression",
            type = AttributeType.STRING
    )
    public String cronexpression() default "* * * * *";

    @AttributeDefinition(
            name = "Concurrent",
            description = "Concurrent of Scheduler",
            type = AttributeType.BOOLEAN
    )
    public boolean concurrent() default true;

}
