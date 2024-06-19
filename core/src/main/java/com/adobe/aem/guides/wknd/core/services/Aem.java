package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;
@ObjectClassDefinition(name = "AEM-DEMO",description = "This is AEM-DEMO Interface")
public @interface Aem{

    @AttributeDefinition(
            name = "Experience",
            description = "Enter the Work Experience",
            type = AttributeType.INTEGER
    )
    public int exp();

    @AttributeDefinition(
            name = "Location",
            description = "Enter the Location",
            type = AttributeType.STRING
    )
    public String loc();

    @AttributeDefinition(
            name = "Role",
            description = "Select the Role",
            type = AttributeType.STRING,
            options = {
                    @Option(label = "AEM-JAVA", value = "Back-end"),
                    @Option(label = "QA", value = "Testing"),
                    @Option(label = "HTML-CSS", value = "Front-End")
            }

    )
    public String desig() default "AEM-JAVA";

    @AttributeDefinition(
            name = "Name",
            description = "Enter Employee Name",
            type = AttributeType.STRING
    )
    public String name();

}
