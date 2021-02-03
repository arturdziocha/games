package adapter.repository.inmemory.entity;

import io.vavr.collection.Set;

public class ShipPointsInMemory {
    private final String shipId;
    private final Set<String> pointIds;

    public static class Builder {
        private String shipId;
        private Set<String> pointIds;

        Builder shipId(final String shipId) {
            this.shipId = shipId;
            return this;
        }

        Builder pointIds(final Set<String> pointIds) {
            this.pointIds = pointIds;
            return this;
        }

        ShipPointsInMemory build() {
            return new ShipPointsInMemory(this);
        }

    }

    private ShipPointsInMemory(Builder builder) {
        this.shipId = builder.shipId;
        this.pointIds = builder.pointIds;
    }

    public String getShipId() {
        return shipId;
    }

    public Set<String> getPointIds() {
        return pointIds;
    }

}
