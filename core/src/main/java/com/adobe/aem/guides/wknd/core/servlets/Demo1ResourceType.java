package com.adobe.aem.guides.wknd.core.servlets;

import com.day.crx.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = " weretail/components/structure/page",
        methods = {HttpConstants.METHOD_GET,HttpConstants.METHOD_POST},
        extensions = "txt"

)
public class Demo1ResourceType extends SlingAllMethodsServlet {

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        JsonObjectBuilder json= Json.createObjectBuilder();
        json.add("name","ISH");
        json.add("Location","Bengaluru");
        response.getWriter().write(json.build().toString());


    }
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
