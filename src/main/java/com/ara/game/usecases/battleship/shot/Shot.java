package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.enums.PointStatus;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;

final class Shot {
    private final PlayerDto player;
    private final PointDto point;
    private final PointStatus pointStatus;


    private Shot(Builder builder) {
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

    static class Builder {
        private PlayerDto player;
        private PointDto point;
        private PointStatus pointStatus;

        Shot build(Builder builder) {
            return new Shot(this);
        }

        Builder player(final PlayerDto player) {
            this.player = player;
            return this;
        }

        Builder point(final PointDto point) {
            this.point = point;
            return this;
        }

        Builder pointStatus(final PointStatus pointStatus) {
            this.pointStatus = pointStatus;
            return this;
        }
    }


}
