package pl.kl.weatherservice.location;

public final class LocationTestHelper {

    static Location provideLocationToCreate() {
        return Location.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static Location provideLocationToCreateWithEmptyRegion() {
        return Location.builder()
                .city("Gdansk")
                .region(null)
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    public static CreateLocationRequest provideCreateLocationRequest() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideCreateLocationRequestWithEmptyCity() {
        return CreateLocationRequest.builder()
                .city("")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideCreateLocationRequestWithNullCity() {
        return CreateLocationRequest.builder()
                .city(null)
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideCreateLocationRequestWithEmptyCountry() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideCreateLocationRequestWithNullCountry() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country(null)
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideCreateLocationRequestWithLatitudeOutOfRange() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(154.3)
                .longitude(18.6)
                .build();
    }

    static CreateLocationRequest provideCreateLocationRequestWithLongitudeOutOfRange() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(218.6)
                .build();
    }

    static UpdateLocationRequest provideUpdateLocationRequest(String idOfLocationToUpdate) {
        return UpdateLocationRequest.builder()
                .id(idOfLocationToUpdate)
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.35)
                .longitude(18.67)
                .build();
    }
}
