package adapter.repository.inmemory.entity;

import adapter.repository.inmemory.EntityInMemory;
import io.vavr.collection.Set;

public final class GameInMemory extends EntityInMemory {
    private final Set<String> players;
    private final String currentPlayer;
    private final boolean isStarted;

    private GameInMemory(Builder builder) {
        super(builder);
        this.players = builder.players;
        this.currentPlayer = builder.currentPlayer;
        this.isStarted = builder.isStarted;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public static class Builder extends EntityInMemory.Builder<Builder> {
        private Set<String> players;
        private String currentPlayer;
        private boolean isStarted;

        public Builder players(final Set<String> players) {
            this.players = players;
            return self();
        }

        public Builder currentPlayer(final String currentPlayer) {
            this.currentPlayer = currentPlayer;
            return self();
        }

        public Builder isStarted(final boolean isStarted) {
            this.isStarted = isStarted;
            return this;
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
