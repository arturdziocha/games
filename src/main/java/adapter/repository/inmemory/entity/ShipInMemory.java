package adapter.repository.inmemory.entity;

import adapter.repository.inmemory.EntityInMemory;

public class ShipInMemory extends EntityInMemory {
    private final String shipClassShortName;
    private final Integer health;

    public static class Builder extends EntityInMemory.Builder<Builder> {
        private String shipClassShortName;
        private Integer health;

        public Builder shipClassShortName(final String shipClassShortName) {
            this.shipClassShortName = shipClassShortName;
            return self();
        }

        public Builder health(final Integer health) {
            this.health = health;
            return self();
        }

        @Override
        protected ShipInMemory build() {
            return new ShipInMemory(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private ShipInMemory(Builder builder) {
        super(builder);
        this.shipClassShortName = builder.shipClassShortName;
        this.health = builder.health;
    }

    public String getShipClassShortName() {
        return shipClassShortName;
    }

    public Integer getHealth() {
        return health;
    }
}
