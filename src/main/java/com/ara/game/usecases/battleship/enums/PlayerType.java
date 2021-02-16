package com.ara.game.usecases.battleship.enums;

import io.vavr.collection.Stream;
import io.vavr.control.Option;

public enum PlayerType {
    HUMAN_PLAYER("1", "Human Player"),
    COMPUTER_PLAYER("2", "Computer Player");
    private final String id;
    private final String name;

    PlayerType(final String id, final String name) {
        this.name = name;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Option<PlayerType> findById(final String id) {
        return Stream.of(values()).find(val -> val.id.equals(id));
    }
}
