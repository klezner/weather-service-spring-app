package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/location")
class LocationController {

    private final LocationMapper locationMapper;
    private final LocationService locationService;

    @PostMapping()
//    @PreAuthorize("hasRole(T(pl.kl.weatherservice.security.Roles).ADMIN)")
    ResponseEntity<NewLocationResponse> addLocation(@RequestBody @Valid CreateLocationRequest request) {
        Location location = locationService.createLocation(
                request.getCity(),
                request.getRegion(),
                request.getCountry(),
                request.getLatitude(),
                request.getLongitude()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationMapper.mapLocationToNewLocationResponse(location));
    }

    @PutMapping()
//    @PreAuthorize("hasRole(T(pl.kl.weatherservice.security.Roles).ADMIN)")
    ResponseEntity<UpdatedLocationResponse> editLocation(@RequestBody @Valid UpdateLocationRequest request, @RequestHeader("If-Match") Long version) {
        Location location = locationService.updateLocation(
                request.getId(),
                request.getCity(),
                request.getRegion(),
                request.getCountry(),
                request.getLatitude(),
                request.getLongitude(),
                version
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .eTag(String.valueOf(version))
                .body(locationMapper.mapLocationToUpdatedLocationResponse(location));
    }
}
