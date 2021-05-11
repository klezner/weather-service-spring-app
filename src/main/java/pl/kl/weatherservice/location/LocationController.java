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
//    @PreAuthorize("hasRole('Roles.ADMIN')")
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
//    @PreAuthorize("hasRole('Roles.ADMIN')")
    ResponseEntity<UpdatedLocationResponse> updateLocation(@RequestBody @Valid UpdateLocationRequest request) {
        Location location = locationService.updateLocation(
                request.getId(),
                request.getCity(),
                request.getRegion(),
                request.getCountry(),
                request.getLatitude(),
                request.getLongitude()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationMapper.mapLocationToUpdatedLocationResponse(location));
    }
}
