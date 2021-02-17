package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;

import io.vavr.collection.Set;

final class ShipPoints {
    private final ShipDto ship;
    private final Set<PointDto> points;

    static class Builder {
        private ShipDto ship;
        private Set<PointDto> points;

        Builder ship(final ShipDto ship) {
            this.ship = ship;
            return this;
        }

        Builder points(Set<PointDto> points) {
            this.points = points;
            return this;
        }

        ShipPoints build() {
            return new ShipPoints(this);
        }
    }

    private ShipPoints(Builder builder) {
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
