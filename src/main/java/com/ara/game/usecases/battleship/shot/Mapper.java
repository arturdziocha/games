package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.shot.dto.ShotDto;

class Mapper {
    ShotDto mapToDto(Shot shot) {
        return ShotDto.builder().player(shot.getPlayer()).point(shot.getPoint()).pointStatus(shot.getPointStatus()).shotTime(shot.getShotTime()).build();
    }
}
