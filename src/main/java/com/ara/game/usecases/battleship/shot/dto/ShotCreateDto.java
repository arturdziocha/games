package com.ara.game.usecases.battleship.shot.dto;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;

public class ShotCreateDto {
    private final GameDto game;
    private final PointDto point;

    private ShotCreateDto(Builder builder) {
        this.game = builder.game;
        this.point = builder.point;
    }

    public GameDto getGame() {
        return game;
    }

    public PointDto getPoint() {
        return point;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GameDto game;
        private PointDto point;

        public Builder withGame(final GameDto game) {
            this.game = game;
            return this;
        }

        public Builder withPoint(final PointDto point) {
            this.point = point;
            return this;
        }

        public ShotCreateDto build() {
            return new ShotCreateDto(this);
        }
    }
}
