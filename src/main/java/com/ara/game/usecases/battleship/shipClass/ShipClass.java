package com.ara.game.usecases.battleship.shipClass;

enum ShipClass {
    BARCA("Barca", "b", 1),
    PATROL_BOAT("Patrol Boat", "pb", 2),
    SUBMARINE("Submarine", "s", 3),
    DESTROYER("Destroyer", "d", 3),
    BATTLESHIP("Battleship", "bp", 4),
    CARRIER("Carrier", "c", 5);
    private final String name;
    private final String shortName;
    private final Integer size;

    ShipClass(final String name, final String shortName, final Integer size) {
        this.name = name;
        this.shortName = shortName;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Integer getSize() {
        return size;
    }
}
