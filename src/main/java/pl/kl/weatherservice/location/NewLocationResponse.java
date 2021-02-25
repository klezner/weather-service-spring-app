package pl.kl.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
class NewLocationResponse {
    private String city;
    private String region;
    private String country;
    private Integer longitude;
    private String direction;
    private Integer latitude;
    private String latitudeDirection;
}
