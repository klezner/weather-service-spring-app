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

    // todo - model required by frontend
    //  id - number (string for UUID), city - string, region - string, country - string, longitude - string (enum), latitude - string (enum)
    //  enum - "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
}
