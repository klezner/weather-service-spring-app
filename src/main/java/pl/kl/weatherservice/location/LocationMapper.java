package pl.kl.weatherservice.location;

import org.springframework.stereotype.Component;

@Component
class LocationMapper {

    NewLocationResponse mapLocationToNewLocationResponse(Location location) {
        return NewLocationResponse.builder()
                .id(location.getId())
                .city(location.getCity())
                .region(location.getRegion())
                .country(location.getCountry())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }

    UpdatedLocationResponse mapLocationToUpdatedLocationResponse(Location location) {
        return UpdatedLocationResponse.builder()
                .id(location.getId())
                .city(location.getCity())
                .region(location.getRegion())
                .country(location.getCountry())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
