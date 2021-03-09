package pl.kl.weatherservice.location;

final class LocationTestHelper {

    static Location provideLocationGdansk() {
        return Location.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }

    static Location provideLocationCochabamba() {
        return Location.builder()
                .city("Cochabamba")
                .region(null)
                .country("Bolivia")
                .latitude(-17.4)
                .longitude(-66.1)
                .build();
    }

    static CreateLocationRequest provideLocationRequestGdansk() {
        return CreateLocationRequest.builder()
                .city("Gdansk")
                .region("Pomeranian")
                .country("Poland")
                .latitude(54.3)
                .longitude(18.6)
                .build();
    }
}
