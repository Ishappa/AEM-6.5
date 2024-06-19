package com.adobe.aem.guides.wknd.core.schedulers;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import javax.smartcardio.ATR;

@ObjectClassDefinition(name = "Demo2_shedular",description = "This is Demo_shecdular Config")
public @interface Demo2Config {

    @AttributeDefinition(
            name = "Name",
            description = "Enter the name",
            type = AttributeType.STRING
    )
    public String schedulerName() default "Demo2";

    @AttributeDefinition(
            name = "Cron Expression",
            description = "Enter the Cron Expression",
            type = AttributeType.STRING


    )
    public String cronExpresion() default "0 * * ? * *" ;

//    @AttributeDefinition(
//            name = "Concurrent",
//            description = "concurrent of demo2 schedular",
//            type = AttributeType.BOOLEAN
//    )
//    public boolean concurrent() default true;

}
