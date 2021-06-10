package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

public final class GameJoinerDto {
    private final GameDto game;
    private final PlayerDto playerToJoin;

    private GameJoinerDto(Builder builder) {
        this.game = builder.game;
        this.playerToJoin = builder.playerToJoin;
    }

    public GameDto getGame() {
        return game;
    }

    public PlayerDto getPlayerToJoin() {
        return playerToJoin;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GameDto game;
        private PlayerDto playerToJoin;

        public Builder withGame(final GameDto game) {
            this.game = game;
            return this;
        }

        public Builder withPlayerToJoin(final PlayerDto player) {
            this.playerToJoin = player;
            return this;
        }

        public GameJoinerDto build() {
            return new GameJoinerDto(this);
        }
    }

}
