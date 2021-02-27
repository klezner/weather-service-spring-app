package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.weatherservice.exceptions.EmptyInputException;
import pl.kl.weatherservice.exceptions.InputOutOfRangeException;

@Component
@RequiredArgsConstructor
class LocationsService {

    private final Double NORTH_LIMIT = 90.0;
    private final Double SOUTH_LIMIT = -90.0;
    private final Double WEST_LIMIT = -180.0;
    private final Double EAST_LIMIT = 180.0;

    private final LocationsRepository locationsRepository;

    Location createLocation(String city, String region, String country, Integer longitude, Integer latitude) {
        if (city.isEmpty()) {
            throw new EmptyInputException("Value cannot be empty.");
        }
        if (country.isEmpty()) {
            throw new EmptyInputException("Value cannot be empty.");
        }
        if (longitude < WEST_LIMIT || longitude > EAST_LIMIT) {
            throw new InputOutOfRangeException("Value is out of range.");
        }
        if (latitude < SOUTH_LIMIT || longitude > NORTH_LIMIT) {
            throw new InputOutOfRangeException("Value is out of range.");
        }

        Location location = new Location();
        location.setCity(city);
        location.setRegion(region); // todo if user pass there an empty value (eg. "  ") you can set null
        location.setCountry(country);
        location.setLongitude(longitude);
        location.setLongitudeDirection(specifyLongitudeDirection(longitude));
        location.setLatitude(latitude);
        location.setLatitudeDirection(specifyLatitudeDirection(latitude));

        return locationsRepository.save(location);
    }

    private LongitudeDirection specifyLongitudeDirection(Integer longitude) {
        if (longitude < 0) {
            return LongitudeDirection.WEST;
        }
        return LongitudeDirection.EAST;
    }

    private LatitudeDirection specifyLatitudeDirection(Integer latitude) {
        if (latitude < 0) {
            return LatitudeDirection.SOUTH;
        }
        return LatitudeDirection.NORTH;
    }
}
