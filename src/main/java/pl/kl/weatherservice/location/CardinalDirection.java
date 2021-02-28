package pl.kl.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardinalDirection {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private String abbreviation;
}
