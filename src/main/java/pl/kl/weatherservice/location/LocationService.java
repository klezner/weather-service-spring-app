package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
class LocationService {

    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double latitude, Double longitude) {
        Location location = new Location();
        location.setCity(city);
        Optional.ofNullable(region).filter(Predicate.not(String::isBlank)).ifPresent(location::setRegion);
        location.setCountry(country);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return locationRepository.save(location);
    }

    Location updateLocation(String id, String city, String region, String country, Double latitude, Double longitude) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Location was found for id: " + id));

        location.setCity(city);
        Optional.ofNullable(region).filter(Predicate.not(String::isBlank)).ifPresent(location::setRegion);
        location.setCountry(country);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return locationRepository.save(location);
    }
}
