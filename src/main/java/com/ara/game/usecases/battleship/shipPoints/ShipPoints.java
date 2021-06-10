package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import io.vavr.collection.Set;

final class ShipPoints {
    private final ShipDto ship;
    private final Set<PointDto> points;

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

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private ShipDto ship;
        private Set<PointDto> points;

        Builder withShip(final ShipDto ship) {
            this.ship = ship;
            return this;
        }

        Builder withPoints(Set<PointDto> points) {
            this.points = points;
            return this;
        }

        ShipPoints build() {
            return new ShipPoints(this);
        }
    }
}
