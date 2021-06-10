package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.enums.PointStatus;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;

import java.time.LocalDateTime;

final class Shot {
    private final PlayerDto player;
    private final PointDto point;
    private final PointStatus pointStatus;
    private final LocalDateTime shotTime;


    private Shot(Builder builder) {
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

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private PlayerDto player;
        private PointDto point;
        private PointStatus pointStatus;
        private LocalDateTime shotTime;

        Shot build() {
            return new Shot(this);
        }

        Builder withPlayer(final PlayerDto player) {
            this.player = player;
            return this;
        }

        Builder withPoint(final PointDto point) {
            this.point = point;
            return this;
        }

        Builder withPointStatus(final PointStatus pointStatus) {
            this.pointStatus = pointStatus;
            return this;
        }

        Builder withShotTime(final LocalDateTime shotTime) {
            this.shotTime = shotTime;
            return this;
        }
    }
}
