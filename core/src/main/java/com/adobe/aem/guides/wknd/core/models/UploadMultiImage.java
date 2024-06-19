package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.SlingException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.engine.SlingRequestProcessor;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.servlethelpers.internalrequests.SlingInternalRequest;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.hemf.hemfplus.record.HemfPlusRecordType.object;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UploadMultiImage {

    @ChildResource
    private List<ChildUploadMultiImage> fields;

    public List<ChildUploadMultiImage> getFields() {
        return fields;
    }
    private List<String> responString=new ArrayList<>();

    public List<String> getResponString() {
        return responString;
    }

    @SlingObject
    private ResourceResolver resourceResolver;

    @OSGiService
    private SlingRequestProcessor slingRequestProcessor;

    @PostConstruct
    public void init(){
        // Your existing logic for uploading the image goes here

//        for (ChildUploadMultiImage value : fields) {
            try {
                SlingInternalRequest internalRequest = new SlingInternalRequest(resourceResolver, slingRequestProcessor, "/bin/SingleImageUploadServletMulti");
//                internalRequest.withParameter("src",value.getPath());
//                internalRequest.withParameter("imgName",value.getName());
                Map<String,Object> map = new HashMap<>();
                for (ChildUploadMultiImage img: fields) {
                    map.put(img.getName(),img.getPath());
                }
                internalRequest.withParameters(map);


                 String str  = internalRequest
                        .withRequestMethod("GET")
                        .execute()
                        .checkStatus(200)
                        .checkResponseContentType("text/plain")
                        .getResponseAsString();

                System.out.println("model"+str);

                str=str.substring(1,str.length()-1);

                String[] split = str.split(",");
                System.out.println(fields.size());
                System.out.println("split:0"+split[0]);
                for(String s:split){
                    responString.add(s);
                    System.out.println(s);
                 }


            } catch (IOException e) {
                System.out.println(e.toString());
            }
//        }
    }

}
