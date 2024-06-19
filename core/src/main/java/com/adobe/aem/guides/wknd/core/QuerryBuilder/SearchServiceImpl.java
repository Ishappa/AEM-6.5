package com.adobe.aem.guides.wknd.core.QuerryBuilder;
import com.day.cq.search.QueryBuilder;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component(service = SearchResult.class,immediate = true)
public class SearchServiceImpl implements SearchResult{

   private static final Logger LOG= LoggerFactory.getLogger(SearchServiceImpl.class);

    @SlingObject
    SlingHttpServletRequest request;

    @Reference
    QueryBuilder queryBuilder;

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Activate
    public void activate(){
     LOG.info("__Method is Activated__");
    }



    public Map<String, String> getQueryMap(String searchtext) {

        Map<String,String> queryMap=new HashMap<String,String>();
        queryMap.put("path","/content/ish");
        queryMap.put("type","cq:Page");
        queryMap.put("fulltext",searchtext);
        queryMap.put("p.limit",Long.toString(-1));



        return queryMap;
    }


    @Override
    public JsonObject searchResult(String searchText) {

        JsonObject searchResult=new JsonObject();
        ResourceResolver resourceResolver=request.getResourceResolver();

        return null;
    }
}
