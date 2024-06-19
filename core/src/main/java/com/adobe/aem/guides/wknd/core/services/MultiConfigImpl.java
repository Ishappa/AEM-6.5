package com.adobe.aem.guides.wknd.core.services;

import com.adobe.aem.guides.wknd.core.services.MultiConfig;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import java.util.ArrayList;
import java.util.List;
@Component(service = MultiConfig.class ,configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = ApiMultiConfig.class, factory = true)
public class MultiConfigImpl implements MultiConfig {
    private String storekeys;
    private List<MultiConfig> configsList;
    @Activate
    @Modified
    protected void activate(final ApiMultiConfig config) {
        storekeys = config.storeKey();
    }

    @Reference(service = MultiConfig.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindMultiConfig(final MultiConfig config) {
        if (configsList == null){
            configsList = new ArrayList<>();
        }
        configsList.add(config);

    }

    public void unbindMultiConfig(final MultiConfig config) {
        configsList.remove(config);
    }

    @Override
    public String getStoreKey() {
        return storekeys;
    }

    @Override
    public List<MultiConfig> getAllConfigs() {
        return configsList;
    }
}