package com.tictactoe.service.game;

import com.tictactoe.domainobject.GameDO;
import com.tictactoe.domainvalue.GameState;
import com.tictactoe.domainvalue.PlayerNumber;
import com.tictactoe.exception.PlaceAlreadyOccupiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("singleton")
public class GameService {

    private static final Logger LOG = LoggerFactory.getLogger(com.tictactoe.service.game.GameService.class);

    public GameDO create() {
        GameDO game = new GameDO(PlayerNumber.PLAYER_ONE, GameState.IN_PROGRESS, BoardUtil.createEmpty());
        GameDO.setInstance(game);

        return game;
    }

    public GameDO getGame() {

        return GameDO.getInstance();
    }

    public GameDO takeTurn(GameDO game, String tileId) {
        if (game.getState() != GameState.IN_PROGRESS) {
            LOG.warn("Game finished..", game);
            return null;
        }

        String[] indices = tileId.split("-");
        if (indices.length != 2) {
            LOG.warn("Pass the tile id with in range..", game);
            throw new PlaceAlreadyOccupiedException("illegal move");
        }

        BoardTile tile;
        if (game.getNextMove() == PlayerNumber.PLAYER_ONE) {
            tile = BoardTile.X;
            game.setNextMove(PlayerNumber.PLAYER_TWO);
        } else {
            tile = BoardTile.O;
            game.setNextMove(PlayerNumber.PLAYER_ONE);
        }

        int rowIndex = Integer.parseInt(indices[0]);
        int columnIndex = Integer.parseInt(indices[1]);

        String occupied = game.getRows().get(rowIndex).get(columnIndex);
        if(occupied != null && !occupied.isEmpty()) {
            LOG.warn("The place already taken..", game);
            throw new PlaceAlreadyOccupiedException("illegal move");
        }
        game.getRows().get(rowIndex).set(columnIndex, tile.toString());

        GameState state = evaluateGameState(game.getRows());
        game.setState(state);
        if (state != GameState.IN_PROGRESS) {
            game.setNextMove(null);
        }

        return game;

    }

    private GameState evaluateGameState(List<List<String>> rows) {
        for (List<String> line : BoardUtil.getAllLines(rows)) {
            String firstTile = line.get(0);
            if (firstTile.isEmpty()) {
                continue;
            }

            if (line.stream().allMatch(tile -> tile.equals(firstTile))) {
                return firstTile.equals(BoardTile.X.toString()) ? GameState.PLAYER_ONE_WIN : GameState.PLAYER_TWO_WIN;
            }
        }

        for (List<String> row : rows) {
            if (row.stream().anyMatch(String::isEmpty)) {
                return GameState.IN_PROGRESS;
            }
        }

        return GameState.DRAW;
    }
}
