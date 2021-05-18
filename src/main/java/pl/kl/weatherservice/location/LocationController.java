package pl.kl.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/location")
class LocationController {

    private final LocationMapper locationMapper;
    private final LocationService locationService;

    @PostMapping()
    @PreAuthorize("hasRole('Roles.ADMIN')")
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

    @GetMapping()
    Page<Location> getLocationsPaginated(@RequestParam(name = "offset") int offset, @RequestParam(name = "sort") String sort) {

        return locationService.listLocationsPaginated(PageRequest.of(offset, 5, Sort.by(Sort.Direction.fromString(sort), "city")));
    }
}
