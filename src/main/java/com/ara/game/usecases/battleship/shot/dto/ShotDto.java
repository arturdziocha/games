package com.ara.game.usecases.battleship.shot.dto;

import com.ara.game.usecases.battleship.enums.PointStatus;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;

import java.time.LocalDateTime;

public class ShotDto {
    private final PlayerDto player;
    private final PointDto point;
    private final PointStatus pointStatus;
    private final LocalDateTime shotTime;

    private ShotDto(Builder builder) {
        this.player = builder.player;
        this.point = builder.point;
        this.pointStatus = builder.pointStatus;
        this.shotTime = builder.shotTime;
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

    public LocalDateTime getShotTime() {
        return shotTime;
    }

    public static class Builder {
        private PlayerDto player;
        private PointDto point;
        private PointStatus pointStatus;
        private LocalDateTime shotTime;

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

        public Builder shotTime(final LocalDateTime shotTime) {
            this.shotTime = shotTime;
            return this;
        }

        public ShotDto build() {
            return new ShotDto(this);
        }
    }
}
