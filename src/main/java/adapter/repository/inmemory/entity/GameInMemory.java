package adapter.repository.inmemory.entity;

import adapter.repository.inmemory.EntityInMemory;
import io.vavr.collection.Set;

import java.time.LocalDateTime;

public final class GameInMemory extends EntityInMemory {
    private final Set<String> players;
    private final String currentPlayer;
    private final Integer size;
    private final boolean isStarted;
    private final boolean isFinished;
    private final LocalDateTime createdTime;

    private GameInMemory(Builder builder) {
        super(builder);
        this.players = builder.players;
        this.size = builder.size;
        this.currentPlayer = builder.currentPlayer;
        this.isStarted = builder.isStarted;
        this.isFinished = builder.isFinished;
        this.createdTime = builder.createdTime;
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

    public boolean isFinished() {
        return isFinished;
    }

    public Integer getSize() {
        return size;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public static class Builder extends EntityInMemory.Builder<Builder> {
        private Set<String> players;
        private String currentPlayer;
        private Integer size;
        private boolean isStarted;
        private boolean isFinished;
        private LocalDateTime createdTime;

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
            return self();
        }

        public Builder size(final Integer size) {
            this.size = size;
            return self();
        }

        public Builder isFinished(final boolean isFinished) {
            this.isFinished = isFinished;
            return self();
        }

        public Builder createdTime(final LocalDateTime createdTime) {
            this.createdTime = createdTime;
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
