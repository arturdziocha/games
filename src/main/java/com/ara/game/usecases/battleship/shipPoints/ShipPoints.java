package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import io.vavr.collection.Set;

final class ShipPoints {
    private final String shipId;
    private final Set<PointDto> points;

    static class Builder {
        private String shipId;
        private Set<PointDto> points;

        Builder shipId(final String shipId) {
            this.shipId = shipId;
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
        this.shipId = builder.shipId;
        this.points = builder.points;
    }

    public String getShipId() {
        return shipId;
    }

    public Set<PointDto> getPoints() {
        return points;
    }
}
