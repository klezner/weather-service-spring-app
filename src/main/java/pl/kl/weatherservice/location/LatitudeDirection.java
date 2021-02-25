package pl.kl.weatherservice.location;

public enum LatitudeDirection {
    NORTH("N"),
    SOUTH("S");

    final String abbreviation;

    LatitudeDirection(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
