package pl.kl.weatherservice.location;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class UpdateLocationRequest {

    @NotBlank(message = "Id is mandatory!")
    private String id;
    @NotBlank(message = "City is mandatory!")
    private String city;
    private String region;
    @NotBlank(message = "Country is mandatory!")
    private String country;
    @Min(value = -90, message = "Latitude value is too small! The correct latitude range is from -90 to 90.")
    @Max(value = 90, message = "Latitude value is too large! The correct latitude range is from -90 to 90.")
    private Double latitude;
    @Min(value = -180, message = "Longitude value is too small! The correct longitude range is from -180 to 180.")
    @Max(value = 180, message = "Longitude value is too large! The correct longitude range is from -180 to 180.")
    private Double longitude;
}
