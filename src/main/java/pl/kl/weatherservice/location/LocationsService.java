package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.kl.weatherservice.exceptions.EmptyInputException;
import pl.kl.weatherservice.exceptions.InputOutOfRangeException;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
class LocationsService {

    private final Double NORTH_LIMIT = 90.0;
    private final Double SOUTH_LIMIT = -90.0;
    private final Double EAST_LIMIT = 180.0;
    private final Double WEST_LIMIT = -180.0;

    private final LocationsRepository locationsRepository;

    Location createLocation(String city, String region, String country, Double latitude, Double longitude) {
        validateCity(city);
        validateCountry(country);
        validateLatitude(latitude);
        validateLongitude(longitude);

        Location location = new Location();
        location.setCity(city);
        Optional.ofNullable(region).filter(Predicate.not(String::isBlank)).ifPresent(location::setRegion);
        location.setCountry(country);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return locationsRepository.save(location);
    }

    private void validateCity(String city) {
        if (StringUtils.isBlank(city)) {
            throw new EmptyInputException("City cannot be empty or null!");
        }
    }

    private void validateCountry(String country) {
        if (StringUtils.isBlank(country)) {
            throw new EmptyInputException("Country cannot be empty or null!");
        }
    }

    private void validateLatitude(Double latitude) {
        if (latitude < SOUTH_LIMIT || latitude > NORTH_LIMIT) {
            throw new InputOutOfRangeException("Latitude is out of range! The correct latitude range is from -90 to 90.");
        }
    }

    private void validateLongitude(Double longitude) {
        if (longitude < WEST_LIMIT || longitude > EAST_LIMIT) {
            throw new InputOutOfRangeException("Longitude is out of range! The correct longitude range is from -180 to 180.");
        }
    }
}
