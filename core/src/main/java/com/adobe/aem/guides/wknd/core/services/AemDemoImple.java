package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.*;


@Component(service = DemoInterface.class,immediate = true)
@Designate(ocd = Aem.class)
public class AemDemoImple implements DemoInterface{

    private String name;
    private int exp;

    private String loc;

    private String role;


    @Activate
    public void Activate(Aem aem){
        name=aem.name();
        exp=aem.exp();
        loc=aem.loc();
        role=aem.desig();
    }

    @Override
    public String ename() {
        return name;
    }

    @Override
    public int experience() {
        return exp;
    }

    @Override
    public String location() {
        return loc;
    }

    @Override
    public String role() {
        return role;
    }
}
