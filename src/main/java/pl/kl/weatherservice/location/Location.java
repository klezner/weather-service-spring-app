package pl.kl.weatherservice.location;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String region;
    private String country;
    private Integer longitude;
    private Integer latitude;

    @Enumerated(EnumType.STRING)
    private LongitudeDirection longitudeDirection;

    @Enumerated(EnumType.STRING)
    private LatitudeDirection latitudeDirection;
}
