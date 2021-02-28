package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/location")
class LocationsController {

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

        NewLocationResponse responseBody = new NewLocationResponse(
                location.getId().toString(),
                location.getCity(),
                location.getRegion(),
                location.getCountry(),
                location.getLatitude() + "(" + location.getLatitudeDirection() + ")",
                location.getLongitude() + "(" + location.getLongitudeDirection() + ")"
        );

        return ResponseEntity
                .status(201)
                .body(responseBody);
    }
}
