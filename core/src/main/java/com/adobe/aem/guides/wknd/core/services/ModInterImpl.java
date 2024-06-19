package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component(service = ModInter.class, immediate = true, name = "two")
@ServiceRanking(101)
public class ModInterImpl implements ModInter {

    @Override
    public String message(){
        return "message from ModeInter11";
    }
}
