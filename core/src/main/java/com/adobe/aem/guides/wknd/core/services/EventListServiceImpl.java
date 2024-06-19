package com.adobe.aem.guides.wknd.core.services;

import com.adobe.aem.guides.wknd.core.util.DateComparator;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import java.text.SimpleDateFormat;
import java.util.*;

@Component(service = EventList.class, immediate = true)
public class EventListServiceImpl implements EventList {

    private ResourceResolver resourceResolver;


    @Reference
    private QueryBuilder queryBuilder;

    @Override
    public List<Map<String, Object>> pagelist(int UserEnter, ResourceResolver resolver) {
        int max=10;
        UserEnter= Math.min(max,UserEnter);
        resourceResolver=resolver;
        List<Map<String, Object>> result = new ArrayList<>();

        try {
            String pagePath = "/content";
            String templatePath = "/conf/wknd/settings/wcm/templates/event_template";

            Map<String, String> map = new HashMap<>();
            map.put("path", pagePath);
            map.put("type", "cq:Page");
            map.put("1_property", "jcr:content/cq:template");
            map.put("1_property.value", templatePath);

            Query query = queryBuilder.createQuery(PredicateGroup.create(map), resourceResolver.adaptTo(javax.jcr.Session.class));
            Iterator<Resource> iterator = query.getResult().getResources();

            Calendar calendar = Calendar.getInstance();
            Date time = calendar.getTime();

            while (iterator.hasNext()) {
                Resource pageResource = iterator.next();
                ValueMap pageProperties = pageResource.adaptTo(ValueMap.class);

                Map<String, Object> pageDetails = new HashMap<>();
                String path = pageResource.getPath();
                PageManager p = resourceResolver.adaptTo(PageManager.class);
                Page page = p.getPage(path);

              //  if (time.before(page.getContentResource().getValueMap().get("date", Date.class))) {
                    pageDetails.put("path", pageResource.getPath());
                    pageDetails.put("title", page.getTitle());
                    pageDetails.put("location", page.getContentResource().getValueMap().get("loc", String.class));
                    pageDetails.put("date", new SimpleDateFormat("yyyy-MM-dd").format(page.getContentResource().getValueMap().get("date", Date.class)));
                    result.add(pageDetails);
               // }
            }

            Collections.sort(result, new DateComparator());

        } catch (Exception e) {

            e.printStackTrace();
        }

        return result.subList(0, Math.min(result.size(), UserEnter));//counts is a userenter
    }


}
