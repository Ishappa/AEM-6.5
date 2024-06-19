package com.adobe.aem.guides.wknd.core.servlets;
        import com.adobe.aem.guides.wknd.core.services.WeatherService;
        import org.apache.sling.servlets.annotations.SlingServletPaths;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

        import javax.servlet.Servlet;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/my/weather")
public class WeatherServlet extends HttpServlet {


    @Reference
    private WeatherService weatherService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String city = request.getParameter("city");

        String weatherData = weatherService.getWeather(city);

        response.getWriter().write(weatherData);

    }
}