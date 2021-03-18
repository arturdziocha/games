package com.ara.game.usecases.battleship.game.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;

import io.vavr.collection.Set;

public final class GameDto {
    private final String id;
    private final Set<PlayerDto> players;
    private final PlayerDto currentPlayer;

    public GameDto(Builder builder) {
        this.id = builder.id;
        this.players = builder.players;
        this.currentPlayer = builder.currentPlayer;

    }

    public String getId() {
        return id;
    }

    public Set<PlayerDto> getPlayers() {
        return players;
    }   

    public PlayerDto getCurrentPlayer() {
        return currentPlayer;
    }

    public static class Builder {
        private String id;
        private Set<PlayerDto> players;
        private PlayerDto currentPlayer;


        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder players(final Set<PlayerDto> players) {
            this.players = players;
            return this;
        }        

        public Builder currentPlayer(final PlayerDto currentPlayer) {
            this.currentPlayer = currentPlayer;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }
    }

}
