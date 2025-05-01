package com.hotsse.hrmanager.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NationHolidaysApiRequest implements ApiRequest<NationHolidaysApiResponse> {

    @JsonProperty("solYear")
    private String year;

    @JsonProperty("solMonth")
    private String month;

    @JsonProperty("ServiceKey")
    private String serviceKey;

    @Builder.Default
    @JsonProperty("_type")
    private String type = "json";
}
