package com.adobe.aem.guides.wknd.core.util;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventListUtil {

    private ResourceResolver resourceResolver;

    private int UserEnter;
    private QueryBuilder queryBuilder;

    public EventListUtil(ResourceResolver resourceResolver, QueryBuilder queryBuilder, int UserEnter) {
        this.resourceResolver = resourceResolver;
        this.queryBuilder = queryBuilder;
        this.UserEnter = Math.min(UserEnter, 10);
    }

    public List<Map<String, Object>> pagelist() {
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

            while (iterator.hasNext()) {
                Resource pageResource = iterator.next();
                ValueMap pageProperties = pageResource.adaptTo(ValueMap.class);
                Calendar calendar = Calendar.getInstance();
                Date time = calendar.getTime();

                Map<String, Object> pageDetails = new HashMap<>();
                String path = pageResource.getPath();
                PageManager p = resourceResolver.adaptTo(PageManager.class);
                Page page = p.getPage(path);

            if (time.before(page.getContentResource().getValueMap().get("date", Date.class))) {
                    pageDetails.put("path", pageResource.getPath());
                    pageDetails.put("title", page.getTitle());
                    pageDetails.put("location", page.getContentResource().getValueMap().get("loc", String.class));
                    pageDetails.put("date", new SimpleDateFormat("yyyy-MM-dd").format(page.getContentResource().getValueMap().get("date", Date.class)));
                    result.add(pageDetails);

                }
            }

            Collections.sort(result, new DateComparator());
            System.out.println("Service List: " + result.toString());
        } catch (Exception e) {
            System.out.println("exception" + e.toString());
            e.printStackTrace();
        }

        return result.subList(0, Math.min(result.size(), UserEnter));
    }


}
