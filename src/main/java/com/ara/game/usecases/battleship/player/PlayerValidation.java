package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

class PlayerValidation {
    private final PlayerGateway playerGateway;

    PlayerValidation(final PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;
    }
    Validation<Seq<String>, PlayerCreateDto> validate(PlayerCreateDto player){
        Validation.com
    }
    private Validation<Error, String> validateEmpty(String field){
        
    }
    
}
