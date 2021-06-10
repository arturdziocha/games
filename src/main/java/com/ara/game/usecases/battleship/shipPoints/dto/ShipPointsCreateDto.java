package com.ara.game.usecases.battleship.shipPoints.dto;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import io.vavr.collection.Set;

public class ShipPointsCreateDto {
    private final ShipDto ship;
    private final Set<PointDto> points;

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ShipDto ship;
        private Set<PointDto> points;

        public Builder withShip(final ShipDto ship) {
            this.ship = ship;
            return this;
        }

        public Builder withPoints(final Set<PointDto> points) {
            this.points = points;
            return this;
        }

        public ShipPointsCreateDto build() {
            return new ShipPointsCreateDto(this);
        }
    }
}
