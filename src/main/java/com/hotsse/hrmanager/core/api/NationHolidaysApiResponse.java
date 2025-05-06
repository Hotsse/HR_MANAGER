package com.hotsse.hrmanager.core.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class NationHolidaysApiResponse implements ApiResponse {

    @JsonProperty("response")
    private NationHolidaysResponse response;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NationHolidaysResponse {

        @JsonProperty("body")
        private NationHolidaysBody body;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NationHolidaysBody {

        @JsonProperty("items")
        private NationHolidaysItems items;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NationHolidaysItems {

        @JsonProperty("item")
        private List<NationHolidaysHoliday> item;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NationHolidaysHoliday {

        private String dateKind;
        private String dateName;
        private String isHoliday;

        @JsonProperty("locdate")
        private String locDate;
        private int seq;
    }
}
