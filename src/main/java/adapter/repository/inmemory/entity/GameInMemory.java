package adapter.repository.inmemory.entity;

import adapter.repository.inmemory.EntityInMemory;

public final class GameInMemory extends EntityInMemory {
    private final String firstPlayer;
    private final String secondPlayer;
    private final String currentPlayer;

    public GameInMemory(Builder builder) {
        super(builder);
        this.firstPlayer = builder.firstPlayer;
        this.secondPlayer = builder.secondPlayer;
        this.currentPlayer = builder.currentPlayer;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public static class Builder extends EntityInMemory.Builder<Builder> {
        private String firstPlayer;
        private String secondPlayer;
        private String currentPlayer;

        public Builder firstPlayer(final String firstPlayer) {
            this.firstPlayer = firstPlayer;
            return self();
        }

        public Builder secondPLayer(final String secondPlayer) {
            this.secondPlayer = secondPlayer;
            return self();
        }

        public Builder currentPlayer(final String currentPlayer) {
            this.currentPlayer = currentPlayer;
            return self();
        }

        @Override
        protected GameInMemory build() {
            return new GameInMemory(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
