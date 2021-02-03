package com.ara.game.usecases.battleship.playerType;

enum PlayerType {
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
}
