package pl.kl.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class CreateLocationRequest {
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
}
