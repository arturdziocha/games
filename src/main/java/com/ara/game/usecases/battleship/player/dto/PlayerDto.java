package com.ara.game.usecases.battleship.player.dto;

import com.ara.game.usecases.battleship.enums.PlayerType;

public final class PlayerDto {
    private final String id;
    private final String name;
    private final PlayerType playerType;

    PlayerDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.playerType = builder.playerType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((playerType == null) ? 0 : playerType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerDto other = (PlayerDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return playerType == other.playerType;
    }

    public static class Builder {
        private String name;
        private String id;
        private PlayerType playerType;

        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withPlayerType(final PlayerType playerType) {
            this.playerType = playerType;
            return this;
        }

        public PlayerDto build() {
            return new PlayerDto(this);
        }
    }

}
