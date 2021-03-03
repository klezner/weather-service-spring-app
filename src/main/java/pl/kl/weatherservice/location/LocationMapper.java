package pl.kl.weatherservice.location;

import org.springframework.stereotype.Component;

@Component
class LocationMapper {

    NewLocationResponse mapLocationToNewLocationResponse(LocationsService locationsService, Location location) {
        return NewLocationResponse.builder()
                .id(location.getId())
                .city(location.getCity())
                .region(location.getRegion())
                .country(location.getCountry())
                .latitude(location.getLatitude() + "(" + locationsService.specifyLatitudeDirection(location.getLatitude()) + ")")
                .longitude(location.getLongitude() + "(" + locationsService.specifyLongitudeDirection(location.getLongitude()) + ")")
                .build();
    }
}
