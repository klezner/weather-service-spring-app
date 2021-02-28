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

    Location createLocation(String city, String region, String country, Double latitude, Double longitude) {
        if (isStringEmpty(city)) {
            throw new EmptyInputException("Value cannot be empty.");
        }
        if (isStringEmpty(country)) {
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
        location.setRegion(checkRegionValue(region));
        location.setCountry(country);
        location.setLatitude(latitude);
        location.setLatitudeDirection(specifyLatitudeDirection(latitude));
        location.setLongitude(longitude);
        location.setLongitudeDirection(specifyLongitudeDirection(longitude));

        return locationsRepository.save(location);
    }

    private boolean isStringEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    private String specifyLongitudeDirection(Double longitude) {
        if (longitude < 0) {
            return CardinalDirection.WEST.getAbbreviation();
        } else if (longitude > 0) {
            return CardinalDirection.EAST.getAbbreviation();
        }
        return null;
    }

    private String specifyLatitudeDirection(Double latitude) {
        if (latitude > 0) {
            return CardinalDirection.NORTH.getAbbreviation();
        } else if (latitude < 0) {
            return CardinalDirection.SOUTH.getAbbreviation();
        }
        return null;
    }

    private String checkRegionValue(String region) {
        if (isStringEmpty(region)) {
            return null;
        }
        return region;
    }
}
