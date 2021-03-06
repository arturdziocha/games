package com.ara.game.usecases.battleship.point.dto;

import com.ara.game.usecases.battleship.enums.Direction;

public final class PointsCreateDto {

    private final Integer size;
    private final PointDto point;
    private final Direction direction;

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

    public Direction getDirection() {
        return direction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer size;
        private PointDto point;
        private Direction direction;

        public Builder withSize(final Integer size) {
            this.size = size;
            return this;
        }

        public Builder withPoint(final PointDto point) {
            this.point = point;
            return this;
        }

        public Builder withDirection(final Direction direction) {
            this.direction = direction;
            return this;
        }

        public PointsCreateDto build() {
            return new PointsCreateDto(this);
        }
    }
}
