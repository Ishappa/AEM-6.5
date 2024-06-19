package com.adobe.aem.guides.wknd.core.servlets;

import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/sitemap2")
public class Task8 extends SlingSafeMethodsServlet {

    @Reference
    private Externalizer linkExternalizer;
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        // Set the content type to JSON
        // Get the parent page path
        String parentPagePath = "/content/wknd/us/en";
        response.setContentType("text/xml");

        // Get the ResourceResolver
        ResourceResolver resolver = request.getResourceResolver();
        PageManager pageManager = resolver.adaptTo(PageManager.class);
        Page page = pageManager.getPage(parentPagePath);

        if (page != null) {
            // Recursively get all child pages
            StringBuilder output = new StringBuilder();
            getAllChildPages(page, output,request);
            response.getWriter().println("<pages>");
            response.getWriter().write(output.toString());
            response.getWriter().println("</pages>");
        } else {
            // Handle the case when the parent page doesn't exist
            response.getWriter().write("{\"error\":\"Parent page not found\"}");
        }
    }

    private void getAllChildPages(Page parentPage, StringBuilder output,SlingHttpServletRequest request) {
        Iterator<Page> pageIterator = parentPage.listChildren();
        while (pageIterator.hasNext()) {
            Page childPage=pageIterator.next();
            output.append("<url>");
            output.append("<loc>"+linkExternalizer.externalLink(request.getResourceResolver(), Externalizer.AUTHOR, childPage.getPath())+".html"+"</loc>");
            String date=new SimpleDateFormat("yyyy-MM-dd").format(childPage.getLastModified().getTime());
            output.append("<lastmod>"+date+"</lastmod>");
            output.append("</url>");

            // Recursive call to get children of the current childPage
            getAllChildPages(childPage, output,request);


        }
        if (output.charAt(output.length() - 1) == ',') {
            output.deleteCharAt(output.length() - 1);
        }
    }
}


