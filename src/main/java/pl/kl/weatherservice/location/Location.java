package pl.kl.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Location {

    // todo yeah, I've read https://thorben-janssen.com/generate-uuids-primary-keys-hibernate/
    //  and https://docs.jboss.org/hibernate/core/3.6/reference/en-US/html/mapping.html#d0e5294
    //  and I think uuid2 generator is good as well

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
    private UUID id;    // todo String is more useful
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private String latitudeDirection;   // todo we agreed this value is unnecessary, it may be calculated based on latitude value
    private Double longitude;
    private String longitudeDirection; // todo same there
}
