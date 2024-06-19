package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.services.EventList;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;


@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EventModel {

    @Inject
    SlingHttpServletRequest request;
    @OSGiService
    EventList eventList;

    @ValueMapValue
    @Default(values = "3")
    private String counts;

    public String getCounts() {
        return counts;
    }

    private List<Map<String, Object>> list;


    public List<Map<String, Object>> getPageinfo() {

//         EventListUtil eventlist= new EventListUtil(request.getResourceResolver(),request.getResourceResolver().adaptTo(QueryBuilder.class),Integer.parseInt(counts));
//        System.out.println(eventlist.pagelist().toString());
//        return eventlist.pagelist();


        return list;
    }
    @PostConstruct
    public void init(){
       list = eventList.pagelist(Integer.parseInt(counts),request.getResourceResolver());
    }


}
