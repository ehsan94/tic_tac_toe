package com.tictactoe.domainobject;

import com.tictactoe.domainvalue.GameState;
import com.tictactoe.domainvalue.PlayerNumber;
import org.hibernate.annotations.Type;

import java.util.List;

public class GameDO {

    private PlayerNumber nextMove;

    private GameState state;

    @Type(type = "json")
    private List<List<String>> rows;

    private GameDO()
    {
    }


    public GameDO(PlayerNumber nextMove, GameState state,  List<List<String>> rows)
    {
        this.nextMove = nextMove;
        this.state = state;
        this.rows = rows;
    }

    public PlayerNumber getNextMove() {
        return nextMove;
    }

    public void setNextMove(PlayerNumber nextMove) {
        this.nextMove = nextMove;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }

    private static class SingletonHolder {
        public static GameDO instance = new GameDO();
    }

    public static GameDO getInstance() {
        return SingletonHolder.instance;
    }

    public static void setInstance(GameDO game) {
         SingletonHolder.instance = game;
    }
}
