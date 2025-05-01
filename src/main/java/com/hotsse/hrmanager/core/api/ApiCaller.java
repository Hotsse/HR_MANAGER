package com.hotsse.hrmanager.core.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ApiCaller {

    private final ApiConfigProperties apiConfigProperties;

    public String getKey(ApiType apiType) {
        ApiConfigProperties.ApiInfo apiInfo = apiConfigProperties.getApiInfo(apiType);
        if (apiInfo == null) {
            throw new IllegalArgumentException("API config not found for ApiType: " + apiType.getCode());
        }
        return apiInfo.getKey();
    }

    public <R extends ApiRequest<T>, T extends ApiResponse> T call(ApiType apiType, R request, Class<T> responseType) {

        ApiConfigProperties.ApiInfo apiInfo = apiConfigProperties.getApiInfo(apiType);
        if (apiInfo == null) {
            throw new IllegalArgumentException("API config not found for ApiType: " + apiType.getCode());
        }

        String url = apiInfo.getUrl();
        String method = apiInfo.getMethod();

        var objectMapper = new ObjectMapper();
        var restTemplate = new RestTemplate();
        Map<String, Object> params = objectMapper.convertValue(request, Map.class);

        String rawResponse;

        if ("GET".equalsIgnoreCase(method)) {
            String uriString = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParams(toMultiValueMap(params))
                    .build(false)
                    .toUriString();
            URI uri = URI.create(uriString);

            rawResponse = restTemplate.getForObject(uri, String.class);
        } else if ("POST".equalsIgnoreCase(method)) {
            rawResponse = restTemplate.postForObject(url, params, String.class);
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }

        try {
            return objectMapper.readValue(rawResponse, responseType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response", e);
        }
    }

    private MultiValueMap<String, String> toMultiValueMap(Map<String, Object> params) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            map.add(entry.getKey(), entry.getValue().toString());
        }
        return map;
    }
}
