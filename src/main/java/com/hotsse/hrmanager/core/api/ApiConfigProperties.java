package com.hotsse.hrmanager.core.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiConfigProperties {

    private Map<String, ApiInfo> configs;

    @Getter
    @Setter
    public static class ApiInfo {
        private String url;
        private String method; // "GET" or "POST"
        private String key;
    }

    public ApiInfo getApiInfo(ApiType apiType) {
        if (configs == null) {
            throw new IllegalStateException("API configs not loaded");
        }
        ApiInfo apiInfo = configs.get(apiType.name());
        if (apiInfo == null) {
            throw new IllegalArgumentException("API config not found for type: " + apiType.name());
        }
        return apiInfo;
    }
}
