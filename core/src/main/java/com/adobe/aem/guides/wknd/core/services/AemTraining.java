package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.*;

@Component( service = AemTrainingInter.class)
@Designate( ocd=AemTraining.Aem.class)
public class AemTraining implements AemTrainingInter {


    @ObjectClassDefinition( name = "AEM Training" , description = "This is a Aem Training")

    public @interface Aem{

        @AttributeDefinition(
                name = "name",
                description = "Enter Name",
                type = AttributeType.STRING
        )
        public String Name() default "user";

        @AttributeDefinition(
                name = "Age",
                description = "Enter Age",
                type = AttributeType.INTEGER
        )
        public int getAge();

        @AttributeDefinition(
                name = "Methods",
                description = "Select Method",
                type = AttributeType.STRING,
                options={
                        @Option(label = "GET",value ="GET Method"),
                        @Option(label = "POST", value="POST Method"),
                        @Option(label = "PUT", value="PUT Method")
        }
        )
        public String getMethod() default "GET";
    }

//    --------------------------------------
    private String name;

    private int age;

    private String method;

    @Activate
    public void Activate(Aem aem){
        name=aem.Name();
        age=aem.getAge();
        method=aem.getMethod();
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getMethod()  {
        return method;
    }
}
