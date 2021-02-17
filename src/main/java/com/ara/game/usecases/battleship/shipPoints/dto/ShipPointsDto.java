package com.ara.game.usecases.battleship.shipPoints.dto;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;

import io.vavr.collection.Set;

public class ShipPointsDto {
    private final ShipDto ship;
    private final Set<PointDto> points;

    public static class Builder {
        private ShipDto ship;
        private Set<PointDto> points;

        public Builder ship(final ShipDto shipId){
            this.ship = shipId;
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
        this.ship = builder.ship;this.points = builder.points;
    }

    public ShipDto getShip() {
        return ship;
    }

    public Set<PointDto> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "ShipPointsDto{" +
                "shipId='" + ship + '\'' +
                ", points=" + points +
                '}';
    }
}
