package com.ara.game.usecases.battleship.shipPoints.dto;

import com.ara.game.usecases.battleship.point.dto.PointDto;

import io.vavr.collection.Set;

public class ShipPointsDto {
    private final String shipId;
    private final Set<PointDto> points;

    public static class Builder {
        private String shipId;
        private Set<PointDto> points;

        public Builder shipId(final String shipId){
            this.shipId = shipId;
            return this;
        }
        public Builder points(final Set<PointDto> points) {
            this.points = points;
            return this;
        }

        public ShipPointsDto build() {
            return new ShipPointsDto(this);
        }
    }

    private ShipPointsDto(Builder builder) {
        this.shipId = builder.shipId;this.points = builder.points;
    }

    public String getShipId() {
        return shipId;
    }

    public Set<PointDto> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "ShipPointsDto{" +
                "shipId='" + shipId + '\'' +
                ", points=" + points +
                '}';
    }
}
