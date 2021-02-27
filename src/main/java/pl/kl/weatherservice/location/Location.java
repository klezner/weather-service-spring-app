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
    private Long id;    // todo you can try to use UUID instead of long
    private String city;
    private String region;
    private String country;
    private Integer longitude;
    private Integer latitude;

    @Enumerated(EnumType.STRING)
    private LongitudeDirection longitudeDirection;  // todo it is unnecessary, you can calculate it based on longitude value

    @Enumerated(EnumType.STRING)
    private LatitudeDirection latitudeDirection;  // todo it is unnecessary, you can calculate it based on latitude value
}
