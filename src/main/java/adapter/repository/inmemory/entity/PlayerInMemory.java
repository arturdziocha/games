package adapter.repository.inmemory.entity;

import adapter.repository.inmemory.EntityInMemory;

public class PlayerInMemory extends EntityInMemory {
    private final String name;
    private final String playerType;


    public static class Builder extends EntityInMemory.Builder<Builder> {
        private String name;
        private String playerType;

        public Builder name(final String name) {
            this.name = name;
            return self();
        }

        public Builder playerType(final String playerType) {
            this.playerType = playerType;
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
        this.playerType = builder.playerType;
    }

    public String getName() {
        return name;
    }

    public String getPlayerType() {
        return playerType;
    }
}
