package com.adobe.aem.guides.wknd.core.servlets;

import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Credentials;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component(service = {
        Servlet.class
}, property = {
        "sling.servlet.methods=get",
        "sling.servlet.paths=/bin/config/magento"
})
public class Authentication extends SlingSafeMethodsServlet {
    @Reference
    private SlingRepository slingRepository;


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        //  System.out.println("times");
        if (isUserAuthenticated(request)) {
//            ServletHelper servletHelper = new ServletHelper();
//            JsonObject jsonObject = new JsonObject();
//                jsonObject.add("config", this.getMagentoConfig());
            // servletHelper.write(response, jsonObject);
            response.getWriter().write("Login Successfully");
        }
        else
        {
            //response.getWriter().write("sorry ");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"SOUTH_SMOKE   \"");
        }
    }

    protected boolean isUserAuthenticated(SlingHttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String base64Credentials = authHeader.substring("Basic ".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":");
            if (parts.length == 2) {
                String username = parts[0];
                String password = parts[1];
                return isValidAdminCredentials(request, username, password);
            }
        }
        return false;
    }

    protected boolean isValidAdminCredentials(SlingHttpServletRequest request, String username, String password) {
        // Create SimpleCredentials with the provided username and password
        Credentials credentials = new SimpleCredentials(username, password.toCharArray());
        // Obtain a JCR session using the provided credentials
        Session session = null;
        try {
            session = slingRepository.login(credentials);
            if (session != null && session.isLive()) {
                // response.getWriter().write("login succes");
                session.logout();
                System.out.println("login succes");
                return true;
            } else {
                // response.getWriter().write("login not succes");
                System.out.println("not succes login");
            }
        } catch (RepositoryException e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
