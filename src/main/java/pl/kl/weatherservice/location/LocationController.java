package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/location")
class LocationController {

    private final LocationMapper locationMapper;
    private final LocationService locationService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
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
}
