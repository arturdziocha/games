package com.ara.game.usecases.battleship.shot.dto;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;

public class ShotCreateDto {

    private final PlayerDto player;
    private final PointDto point;
    private final PlayerDto opponent;

    private ShotCreateDto(Builder builder) {
        this.player = builder.player;
        this.point = builder.point;
        this.opponent = builder.opponent;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public PointDto getPoint() {
        return point;
    }

    public PlayerDto getOpponent() {
        return opponent;
    }

    public static class Builder {
        private PlayerDto player;
        private PointDto point;
        private PlayerDto opponent;

        public Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public Builder point(final PointDto point) {
            this.point = point;
            return this;
        }

        public Builder opponent(final PlayerDto opponent) {
            this.opponent = opponent;
            return this;
        }

        public ShotCreateDto build() {
            return new ShotCreateDto(this);
        }
    }
}
