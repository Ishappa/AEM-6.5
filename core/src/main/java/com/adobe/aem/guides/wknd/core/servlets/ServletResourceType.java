package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component( service = Servlet.class,
        property = {
        "sling.servlet.resourceTypes=wknd/components/training1",
                "sling.servlet.methods=PUT",
                "sling.servlet.methods=GET",
                "sling.servlet.selectors=recent",
                "sling.servlet.extensions=txt",
                "sling.servlet.extensions=json",

        }
)
public class ServletResourceType extends SlingAllMethodsServlet {


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        JsonObjectBuilder json = Json.createObjectBuilder();

        json.add("name","AEM");
        json.add("lname", "Adobe");

        response.getWriter().write(json.build().toString());

    }
}
