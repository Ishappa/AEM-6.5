package com.adobe.aem.guides.wknd.core.services;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import java.util.ArrayList;
import java.util.List;

@Component(service = Demo1Imple.class,immediate = true)
@Designate(ocd = Demo1Imple.demo.class, factory = true)
public class Demo1Imple implements Demo1Interface {

    private static List<Demo1Imple> instances= new ArrayList<>();


    @ObjectClassDefinition(name = "demo1-OSGI",description = "This is Demo1 OSGI config")
    public @interface demo{
        @AttributeDefinition(
                name = "NAME",
                description = "Enter the name",
                type = AttributeType.STRING
        )
        public String name() default "ISH";



        @AttributeDefinition(
                name = "LOCATION",
                description = "Enter the location",
                type = AttributeType.STRING
        )
        public String loc();
    }

    private String cname;
    private String loc;



    @Activate
    public void activate(demo dem){
        cname=dem.name();
        loc= dem.loc();

        instances.add(this);
    }


    @Override
    public String name() {
        return cname;
    }

    @Override
    public String location() {
        return loc;
    }

    @Override
    public List<Demo1Imple> instance() {
        return instances;
    }


}


