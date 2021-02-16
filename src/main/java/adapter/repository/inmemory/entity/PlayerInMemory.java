package adapter.repository.inmemory.entity;

import adapter.repository.inmemory.EntityInMemory;

public class PlayerInMemory extends EntityInMemory {
    private final String name;
    private final String playerTypeId;


    public static class Builder extends EntityInMemory.Builder<Builder> {
        private String name;
        private String playerTypeId;

        public Builder name(final String name) {
            this.name = name;
            return self();
        }

        public Builder playerTypeId(final String playerTypeId) {
            this.playerTypeId = playerTypeId;
            return self();
        }


        @Override
        protected PlayerInMemory build() {
            return new PlayerInMemory(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private PlayerInMemory(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.playerTypeId = builder.playerTypeId;
    }

    public String getName() {
        return name;
    }

    public String getPlayerTypeId() {
        return playerTypeId;
    }
}
