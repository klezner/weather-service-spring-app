package pl.kl.weatherservice.location;

public final class LocationTestHelper {

    static Location provideLocation() {
        return Location.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static Location provideLocationWithEmptyRegion() {
        return Location.builder()
                .city("Gdansk")
                .region(null)
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    public static CreateLocationRequest provideLocationRequest() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideLocationRequestWithEmptyCity() {
        return CreateLocationRequest.builder()
                .city("")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideLocationRequestWithNullCity() {
        return CreateLocationRequest.builder()
                .city(null)
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideLocationRequestWithEmptyCountry() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideLocationRequestWithNullCountry() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country(null)
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideLocationRequestWithLatitudeOutOfRange() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(154.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideLocationRequestWithLongitudeOutOfRange() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(218.6)
                .build();
    }
}
