package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

import io.vavr.control.Option;

public final class GameJoinerDto {
    private final GameDto game;
    private final Option<PlayerDto> secondPlayer;

    private GameJoinerDto(Builder builder) {
        this.game = builder.game;
        this.secondPlayer = builder.opponent;
    }

    public GameDto getGame() {
        return game;
    }

    public Option<PlayerDto> secondPlayer() {
        return secondPlayer;
    }

    public static class Builder {
        private GameDto game;
        private Option<PlayerDto> opponent;

        public Builder game(final GameDto game) {
            this.game = game;
            return this;
        }

        public Builder secondPlayer(final Option<PlayerDto> player) {
            this.opponent = player;
            return this;
        }

        public GameJoinerDto build() {
            return new GameJoinerDto(this);
        }
    }

}
