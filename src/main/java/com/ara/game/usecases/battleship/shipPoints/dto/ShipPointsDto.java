package com.ara.game.usecases.battleship.shipPoints.dto;

import com.ara.game.usecases.battleship.point.dto.PointDto;

import io.vavr.collection.Set;

public class ShipPointsDto {
    private final Set<PointDto> points;

    public static class Builder {
        private Set<PointDto> points;

        public Builder points(final Set<PointDto> points) {
            this.points = points;
            return this;
        }

        public ShipPointsDto build() {
            return new ShipPointsDto(this);
        }
    }

    private ShipPointsDto(Builder builder) {
        this.points = builder.points;
    }


    public Set<PointDto> getPoints() {
        return points;
    }
}
