package com.adobe.aem.guides.wknd.core.services;


import java.util.List;

public interface MultiConfig {
    public String getStoreKey();
    public List<MultiConfig> getAllConfigs();
}