package com.ara.game.usecases.battleship.ship;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import com.ara.game.usecases.common.domain.Entity;
final class Ship extends Entity {
    private final String shipClassShortName;
    private final Integer health;

    public static class Builder extends Entity.Builder<Builder> {
        private String shipClassShortName;
        private Integer health;

        Builder shipClassShortName(String shipClassShortName) {
            this.shipClassShortName = shipClassShortName;
            return self();
        }
        Builder health(Integer health) {
            this.health = health;
            return self();
        }

        @Override
        public Ship build() {
            if(StringUtils.isEmpty(id)) {
                throw new IllegalArgumentException("Id cannot be empty");
            }
            return new Ship(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public Ship(Builder builder) {
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
