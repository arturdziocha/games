package com.ara.game.usecases.battleship.direction;

enum Direction {
    UP("Up", "u"),
    RIGHT("Right", "r"),
    DOWN("Down", "d"),
    LEFT("Left", "l");

    private final String name;
    private final String shortName;

    Direction(final String name, final String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
