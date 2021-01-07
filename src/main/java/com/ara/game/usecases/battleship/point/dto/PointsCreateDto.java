package com.ara.game.usecases.battleship.point.dto;

import com.ara.game.usecases.battleship.direction.dto.DirectionDto;

public final class PointsCreateDto {

    private final Integer size;
    private final PointDto point;
    private final DirectionDto direction;

    public static class Builder {
        private Integer size;
        private PointDto point;
        private DirectionDto direction;

        public Builder size(final Integer size) {
            this.size = size;
            return this;
        }

        public Builder point(final PointDto point) {
            this.point = point;
            return this;
        }

        public Builder direction(final DirectionDto direction) {
            this.direction = direction;
            return this;
        }

        public PointsCreateDto build() {
            return new PointsCreateDto(this);
        }
    }

    private PointsCreateDto(Builder builder) {
        this.size = builder.size;
        this.point = builder.point;
        this.direction = builder.direction;
    }

    public Integer getSize() {
        return size;
    }

    public PointDto getPoint() {
        return point;
    }

    public DirectionDto getDirection() {
        return direction;
    }
}
