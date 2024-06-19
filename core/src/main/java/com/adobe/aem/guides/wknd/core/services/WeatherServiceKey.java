package com.adobe.aem.guides.wknd.core.services;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Weather Service Configuration", description = "Configuration for the Weather Service")
public @interface WeatherServiceKey {

    @AttributeDefinition
            (name = "OpenWeatherMap API Key",
            type = AttributeType.STRING,
            description = "API Key for OpenWeatherMap"
            )
          String apiKey() default "";
}