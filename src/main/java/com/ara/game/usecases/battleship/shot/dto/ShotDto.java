package com.ara.game.usecases.battleship.shot.dto;

import com.ara.game.usecases.battleship.enums.PointStatus;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;

public class ShotDto {
    private final PlayerDto player;
    private final PointDto point;
    private final PointStatus pointStatus;

    private ShotDto(Builder builder) {
        this.player = builder.player;
        this.point = builder.point;
        this.pointStatus = builder.pointStatus;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public PointDto getPoint() {
        return point;
    }

    public PointStatus getPointStatus() {
        return pointStatus;
    }

    public static class Builder {
        private PlayerDto player;
        private PointDto point;
        private PointStatus pointStatus;

        public Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public Builder point(final PointDto point) {
            this.point = point;
            return this;
        }

        public Builder pointStatus(final PointStatus pointStatus) {
            this.pointStatus = pointStatus;
            return this;
        }

        public ShotDto build() {
            return new ShotDto(this);
        }
    }
}
