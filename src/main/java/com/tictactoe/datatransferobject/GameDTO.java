package com.tictactoe.datatransferobject;

import com.tictactoe.domainvalue.GameState;
import com.tictactoe.domainvalue.PlayerNumber;

import java.util.List;

public class GameDTO {
    private PlayerNumber nextMove;

    private GameState gameState;

    private List<List<String>> rows;

    private GameDTO() {
    }

    private GameDTO(PlayerNumber nextMove, GameState gameState, List<List<String>> rows) {
        this.nextMove = nextMove;
        this.gameState = gameState;
        this.rows = rows;
    }

    public static GameDTOBuilder newBuilder() {
        return new GameDTOBuilder();
    }

    public PlayerNumber getNextMove() {
        return nextMove;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public static class GameDTOBuilder {
        private PlayerNumber nextMove;

        private GameState gameState;

        private List<List<String>> rows;

        public GameDTOBuilder setNextMove(PlayerNumber nextMove) {
            this.nextMove = nextMove;
            return this;
        }

        public GameDTOBuilder setGameState(GameState gameState) {
            this.gameState = gameState;
            return this;

        }

        public GameDTOBuilder setRows(List<List<String>> rows) {
            this.rows = rows;
            return this;
        }

        public GameDTO createGameDTO() {
            return new GameDTO( nextMove, gameState, rows);
        }

    }
}
