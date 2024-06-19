package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import java.io.IOException;
import java.nio.file.Paths;

@Component( service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "wknd/components/page",
        selectors = "one",
        extensions = "json"
//        methods = HttpConstants.METHOD_GET
)
//@HttpMethodConstraint(value = "GET")
public class SampleServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        JsonObjectBuilder json = Json.createObjectBuilder();

        json.add("name","AEM");
        json.add("lname", "Adobe");

        response.getWriter().write(json.build().toString());

    }



}
