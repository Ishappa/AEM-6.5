package com.adobe.aem.guides.wknd.core.models;


import com.day.cq.wcm.api.Page;

import java.util.Iterator;

public interface ServiceDemo {
    public Iterator<Page> getPageslist() ;

    public String getText();
}