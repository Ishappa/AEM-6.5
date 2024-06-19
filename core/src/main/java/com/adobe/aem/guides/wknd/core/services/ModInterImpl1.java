package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component(service = ModInter.class, immediate = true,name = "one")
@ServiceRanking(102)
public class ModInterImpl1 implements ModInter{

    @Override
    public String message() {
        return "message from ModeInterOne";
    }
}
