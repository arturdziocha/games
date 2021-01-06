package adapter.repository.entity;

import adapter.repository.EntityInMemory;

public class ShipInMemory extends EntityInMemory {
    private final String shipClassShortName;
    private final Integer health;

    public static class Builder extends EntityInMemory.Builder<Builder> {
        private String shipClassShortName;
        private Integer health;

        public Builder shipClassShortName(String shipClassShortName) {
            this.shipClassShortName = shipClassShortName;
            return self();
        }

        public Builder health(Integer health) {
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

    public ShipInMemory(Builder builder) {
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
