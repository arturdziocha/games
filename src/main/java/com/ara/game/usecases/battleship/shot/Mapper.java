package com.ara.game.usecases.battleship.shot;

import com.ara.game.usecases.battleship.shot.dto.ShotDto;

class Mapper {
    ShotDto mapToDto(Shot shot) {
        return ShotDto.builder().withPlayer(shot.getPlayer()).withPoint(shot.getPoint()).withPointStatus(shot.getPointStatus()).withShotTime(shot.getShotTime()).build();
    }
}
