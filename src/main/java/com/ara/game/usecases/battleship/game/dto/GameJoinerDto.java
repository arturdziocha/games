package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

public final class GameJoinerDto {
    private final GameDto game;
    private final PlayerDto player;

    private GameJoinerDto(Builder builder) {
        this.game = builder.game;
        this.player = builder.player;
    }

    public GameDto getGame() {
        return game;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public static class Builder {
        private GameDto game;
        private PlayerDto player;

        public Builder game(final GameDto game) {
            this.game = game;
            return this;
        }

        public Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public GameJoinerDto build() {
            return new GameJoinerDto(this);
        }
    }

}
