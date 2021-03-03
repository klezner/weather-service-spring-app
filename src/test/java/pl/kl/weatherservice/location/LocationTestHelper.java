package pl.kl.weatherservice.location;

final class LocationTestHelper {
    static final Location LOCATION_GDANSK = Location.builder()
            .city("Gdansk")
            .region("Pomeranian")
            .country("Poland")
            .latitude(54.3)
            .longitude(18.6)
            .build();

    static final Location LOCATION_COCHABAMBA = Location.builder()
            .city("Cochabamba")
            .region(null)
            .country("Bolivia")
            .latitude(-17.4)
            .longitude(-66.1)
            .build();

    static final CreateLocationRequest LOCATION_REQUEST_GDANSK = CreateLocationRequest.builder()
            .city("Gdansk")
            .region("Pomeranian")
            .country("Poland")
            .latitude(54.3)
            .longitude(18.6)
            .build();

    static final CreateLocationRequest LOCATION_REQUEST_COCHABAMBA = CreateLocationRequest.builder()
            .city("Cochabamba")
            .region(null)
            .country("Bolivia")
            .latitude(-17.4)
            .longitude(-66.1)
            .build();
}
