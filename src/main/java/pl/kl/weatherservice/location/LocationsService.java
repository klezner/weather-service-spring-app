package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
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
        if (isStringEmpty(city)) {
            throw new EmptyInputException("Value cannot be empty.");
        }
        if (isStringEmpty(country)) {
            throw new EmptyInputException("Value cannot be empty.");
        }
        if (latitude < SOUTH_LIMIT || latitude > NORTH_LIMIT) {
            throw new InputOutOfRangeException("Value is out of range.");
        }
        if (longitude < WEST_LIMIT || longitude > EAST_LIMIT) {
            throw new InputOutOfRangeException("Value is out of range.");
        }

        Location location = new Location();
        location.setCity(city);
        location.setRegion(Optional.ofNullable(region).filter(Predicate.not(String::isBlank)).orElse(null));
        location.setCountry(country);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return locationsRepository.save(location);
    }

    private boolean isStringEmpty(String string) {
        return string == null || string.isBlank();
    }

    String specifyLatitudeDirection(Double latitude) {
        if (latitude > 0) {
            return "N";
        } if (latitude < 0) {
            return "S";
        }
        return null;
    }

    String specifyLongitudeDirection(Double longitude) {
        if (longitude < 0) {
            return "W";
        } else if (longitude > 0) {
            return "E";
        }
        return null;
    }
}
