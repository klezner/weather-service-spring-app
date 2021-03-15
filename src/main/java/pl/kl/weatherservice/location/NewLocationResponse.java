package pl.kl.weatherservice.location;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class NewLocationResponse {
    private String id;
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
}
