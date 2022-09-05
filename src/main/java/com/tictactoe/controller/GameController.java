package com.tictactoe.controller;

import com.tictactoe.controller.mapper.GameMapper;
import com.tictactoe.datatransferobject.GameDTO;
import com.tictactoe.domainobject.GameDO;
import com.tictactoe.service.game.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a game board will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1")
public class GameController {

    private final GameService gameService;


    @Autowired
    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @ApiOperation(value = "Create a new Game", notes = "Returns a board of the game with the game state")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Game not found not found")
    })
    @RequestMapping(value = "/create-game", method = RequestMethod.GET)
    public GameDTO createGame()
    {
        GameDO game= gameService.create();
        return GameMapper.makeGameDTO(game);
    }

    @ApiOperation(value = "Player makes new move", notes = "Returns a board of the game with the current game state")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Move Not Allowed")
    })
    @RequestMapping(value = "/make-move", method = RequestMethod.POST)
    public GameDTO makeMove(
            @ApiParam(value = "tile_id", required = true, example = "0-1") final @RequestParam String tileId
    ) {
        GameDO game = gameService.getGame();
        return GameMapper.makeGameDTO(gameService.takeTurn(game, tileId));
    }

}
