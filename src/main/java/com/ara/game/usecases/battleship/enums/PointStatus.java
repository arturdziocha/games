package com.ara.game.usecases.battleship.enums;

public enum PointStatus {
    EMPTY("Empty", " "),
    MISS("Miss", "M"),
    OCCUPIED("Occupied", "O"),
    HIT("Hit", "X"),
    SUNK("Sunk", "S");

    private final String name;
    private final String status;

    PointStatus(final String name, final String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}