package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

public final class GameJoinerDto {
    private final GameDto game;
    private final PlayerDto opponent;

    private GameJoinerDto(Builder builder) {
        this.game = builder.game;
        this.opponent = builder.opponent;
    }

    public GameDto getGame() {
        return game;
    }

    public PlayerDto getOpponent() {
        return opponent;
    }

    public static class Builder {
        private GameDto game;
        private PlayerDto opponent;

        public Builder game(final GameDto game) {
            this.game = game;
            return this;
        }

        public Builder opponent(final PlayerDto player) {
            this.opponent = player;
            return this;
        }

        public GameJoinerDto build() {
            return new GameJoinerDto(this);
        }
    }

}
