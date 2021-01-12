package com.ara.game.usecases.battleship.shipPoints.dto;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import io.vavr.collection.Set;

public class ShipPointsCreateDto {
    private final ShipDto ship;
    private final Set<PointDto> points;

    public static class Builder {
        private ShipDto ship;
        private Set<PointDto> points;

        public Builder ship(final ShipDto ship) {
            this.ship = ship;
            return this;
        }

        public Builder points(final Set<PointDto> points) {
            this.points = points;
            return this;
        }

        public ShipPointsCreateDto build() {
            return new ShipPointsCreateDto(this);
        }
    }

    private ShipPointsCreateDto(Builder builder) {
        this.ship = builder.ship;
        this.points = builder.points;
    }

    public ShipDto getShip() {
        return ship;
    }

    public Set<PointDto> getPoints() {
        return points;
    }
}
