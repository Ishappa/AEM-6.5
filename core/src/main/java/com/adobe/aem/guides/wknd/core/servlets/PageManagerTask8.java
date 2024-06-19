package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.commons.Externalizer;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/sitemap")
public class PageManagerTask8 extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private Externalizer linkExternalizer;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/xml");

        String rootPagePath = "/content/wknd/us"; // Update this with the actual path of your root page

        // Fetch child pages
        ResourceResolver resolver = request.getResourceResolver();
        PageManager pageManager = resolver.adaptTo(PageManager.class);
        Page rootPage = pageManager.getPage(rootPagePath);

        // Check if the root page exists
        if (rootPage != null) {
            // Build XML response
            StringBuilder xmlResponse = new StringBuilder();
//            xmlResponse.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//            xmlResponse.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");
            xmlResponse.append("<page>");

            // Start processing child pages
            getAllChildPages(request, xmlResponse, rootPage);

            xmlResponse.append("</page>");

            // Write the entire XML response after processing
            response.getWriter().write(xmlResponse.toString());
        } else {
            response.getWriter().write("Error: Root page not found.");
        }
    }

    private String formatDate(Date date) {
        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private void getAllChildPages(SlingHttpServletRequest request, StringBuilder xmlResponse, Page rootPage) {
        Iterator<Page> childPages = rootPage.listChildren();
        while (childPages.hasNext()) {
            Page childPage = childPages.next();
            xmlResponse.append("<url>");

            xmlResponse.append("<loc>").append(linkExternalizer.externalLink(request.getResourceResolver(),
                    Externalizer.AUTHOR, childPage.getPath())).append(".html").append("</loc>");

            // Extract and format the last modified date
            Date lastModifiedDate = childPage.getLastModified().getTime();
            String formattedDate = formatDate(lastModifiedDate);
            xmlResponse.append("<lastmod>").append(formattedDate).append("</lastmod>");

            // Add more properties or elements as needed

            xmlResponse.append("</url>");

            // Recursively process child pages
            getAllChildPages(request, xmlResponse, childPage);
        }
    }
}
