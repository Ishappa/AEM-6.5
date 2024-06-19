package com.adobe.aem.guides.wknd.core.services;


        import org.json.JSONException;
        import org.json.JSONObject;
        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Modified;
        import org.osgi.service.metatype.annotations.Designate;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

@Component(service = WeatherService.class, immediate = true)
@Designate(ocd = WeatherServiceKey.class)
public class WeatherServiceImple implements WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImple.class);
    private String apiKey;

    @Activate
    @Modified
    protected void activate(WeatherServiceKey config) {
        this.apiKey = config.apiKey();
    }

    @Override
    public String getWeather(String city) {

        try {
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());

                JSONObject main = jsonResponse.getJSONObject("main");
                double temperature = main.getDouble("temp");

                String description = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");

                LOG.info("Temperature: {} C, Description: {}", temperature, description);

                return "Temperature: " + (temperature-273.15) + " C, Description: " + description;
            } else {
                return "Error: Unable to fetch weather data. Response Code: " + responseCode;
            }

        } catch (IOException e) {

            e.printStackTrace();
            return "Error: Exception while fetching weather data.";
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

