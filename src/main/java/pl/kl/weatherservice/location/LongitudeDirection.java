package pl.kl.weatherservice.location;

enum LongitudeDirection {
    WEST("W"),
    EAST("E");

    final String abbreviation;

    LongitudeDirection(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
