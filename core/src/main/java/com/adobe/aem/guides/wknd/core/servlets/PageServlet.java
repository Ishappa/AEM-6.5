package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)

@SlingServletResourceTypes(
        resourceTypes = {"wcm/foundation/components/page" ,"wknd/components/page"},
        selectors = {"one","two"},
        extensions = {"json"}
)
public class PageServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        JsonObjectBuilder json= Json.createObjectBuilder();
        json.add("fname","Huli");
        json.add("lname","Kudari");

        response.getWriter().write(json.build().toString());
    }
}
