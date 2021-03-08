package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/location")
class LocationsController {

    private final LocationMapper locationMapper;
    private final LocationsService locationsService;

    @PostMapping()
    ResponseEntity<NewLocationResponse> postLocation(@RequestBody CreateLocationRequest request) {
        Location location = locationsService.createLocation(
                request.getCity(),
                request.getRegion(),
                request.getCountry(),
                request.getLatitude(),
                request.getLongitude()
        );

        NewLocationResponse responseBody = locationMapper.mapLocationToNewLocationResponse(locationsService, location);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseBody);
    }
}
