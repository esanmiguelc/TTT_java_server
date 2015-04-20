package tttjava;

import tictactoe.games.TicTacToeGame;
import tictactoe.gamestate.GameState;

public class CurrentGame {

    private static CurrentGame currentGame = new CurrentGame();
    private TicTacToeGame game;
    private GameState state;

    public static CurrentGame getInstance() {
        return currentGame;
    }

    public void setGame(TicTacToeGame game) {
        this.game = game;
    }

    public TicTacToeGame getGame() {
        return game;
    }

    public void resetGame() {
        game = null;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }
}
