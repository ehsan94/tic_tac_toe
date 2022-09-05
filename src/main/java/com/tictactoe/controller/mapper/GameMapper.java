package com.tictactoe.controller.mapper;

import com.tictactoe.datatransferobject.GameDTO;
import com.tictactoe.domainobject.GameDO;

public class GameMapper
{
    public static GameDO makeGameDO(GameDTO gameDTO)
    {
        return new GameDO(gameDTO.getNextMove(), gameDTO.getGameState(), gameDTO.getRows());
    }


    public static GameDTO makeGameDTO(GameDO gameDO)
    {
        GameDTO.GameDTOBuilder gameDTOBuilder = GameDTO.newBuilder()
                .setNextMove(GameDO.getInstance().getNextMove())
                .setGameState(GameDO.getInstance().getState())
                .setRows(GameDO.getInstance().getRows());

        return gameDTOBuilder.createGameDTO();
    }

}
