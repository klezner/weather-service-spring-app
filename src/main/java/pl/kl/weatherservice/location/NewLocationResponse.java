package pl.kl.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
class NewLocationResponse {
    private String id;
    private String city;
    private String region;
    private String country;
    private String latitude;
    private String longitude;
}
