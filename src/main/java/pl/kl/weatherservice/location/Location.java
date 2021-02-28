package pl.kl.weatherservice.location;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
class Location {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private String latitudeDirection;
    private Double longitude;
    private String longitudeDirection;
}
