package com.ara.game.usecases.battleship.shot.dto;

import com.ara.game.usecases.battleship.enums.PointStatus;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;


public class AllShotsDto {
    private final PlayerDto player;
    private final Map<PointDto, PointStatus> shots;

    private AllShotsDto(Builder builder) {
        this.player = builder.player;
        this.shots = builder.shots;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public Map<PointDto, PointStatus> getShots() {
        return shots;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PlayerDto player;
        private Map<PointDto, PointStatus> shots = HashMap.empty();

        public Builder withPlayer(final PlayerDto player) {
            this.player = player;
            return this;
        }

        public Builder add(final PointDto point, final PointStatus pointStatus) {
            shots = shots.put(point, pointStatus);
            return this;
        }

        public AllShotsDto build() {
            return new AllShotsDto(this);
        }
    }
}
