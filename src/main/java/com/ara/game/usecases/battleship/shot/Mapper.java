package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.shot.dto.ShotDto;

class Mapper {
    ShotDto mapToDto(Shot shot) {
        return new ShotDto.Builder().player(shot.getPlayer()).point(shot.getPoint()).pointStatus(shot.getPointStatus()).build();
    }
}
