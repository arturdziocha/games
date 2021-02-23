package com.ara.game.usecases.battleship.enums;

import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import io.vavr.control.Option;

public enum ShipClass {
    BARCA1("Barca1", "b1", 1),
    BARCA2("Barca2", "b2", 1),
    BARCA3("Barca3", "b3", 1),
    PATROL_BOAT1("Patrol Boat1", "pb1", 2),
    PATROL_BOAT2("Patrol Boat2", "pb2", 2),
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

    public static Option<ShipClass> findByName(final String name) {
        return Stream.of(values()).find(val -> val.name.equals(name));
    }

    public static Option<ShipClass> findByShortName(final String shortName) {
        return Stream.of(values()).find(val -> val.getShortName().equals(shortName));
    }

    public static Set<String> findAllShortName() {
        return Stream.of(values()).map(ShipClass::getShortName).toSortedSet(String::compareTo);
    }
}
