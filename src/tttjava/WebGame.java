package tttjava;

import tictactoe.boards.TicTacToeBoard;
import tictactoe.gamestate.GameState;
import tictactoe.participants.Participant;
import tictactoe.participants.ParticipantFactory;
import tictactoe.rules.TicTacToeRules;

import java.util.Arrays;

public class WebGame {
    private final TicTacToeBoard board = new TicTacToeBoard(3);
    private String playedMoves = " ";
    private Participant firstParticipant;
    private Participant secondParticipant;
    private GameState gameState;
    private String move;

    public void addMove(String move) {
        playedMoves += move;
    }

    public String playedMoves() {
        String s = playedMoves.replaceAll("[^0-9]", " ");
        System.out.println(s);
        return (playedMoves.equals(" ")) ? " " : s;
    }

    public void setPlayedMoves(String playedMoves) {
        this.playedMoves = playedMoves;
    }

    public Participant firstParticipant() {
        return firstParticipant;
    }

    public void setFirstParticipant(String firstParticipantType) {
        this.firstParticipant = new ParticipantFactory(Integer.valueOf(firstParticipantType), null, null, null, "X", null).getPlayer();
    }

    public void setSecondParticipant(String secondParticipantType) {
        this.secondParticipant = new ParticipantFactory(Integer.valueOf(secondParticipantType), null, null, null, "O", null).getPlayer();
    }

    public Participant secondParticipant() {
        return secondParticipant;
    }

    public boolean gameOver() {
        return new TicTacToeRules(board).gameOver();
    }

    public void makeState() {
        this.gameState = new GameState(board);
        gameState.setFirstParticipant(firstParticipant);
        gameState.setSecondParticipant(secondParticipant);
        Arrays.asList(playedMoves().split("")).stream()
                .filter((stringPosition) -> stringPosition.matches("[0-9]"))
                .forEach((position) -> gameState.placeNextMove(Integer.valueOf(position)));
    }

    public String getResultMark() {
        TicTacToeRules rules = new TicTacToeRules(board);
        if (rules.checkTie()) {
            return "tie";
        }
        return rules.getWinner().getMark();
    }

    public boolean isNew() {
        return playedMoves.equals(" ");
    }

    public boolean positionAvailable(int position) {
        return gameState.positionAvailable(position);
    }

    public Participant getParticipantAtPosition(int position) {
        return gameState.getParticipantAtPosition(position);
    }

    public void setMove(String move) {
        this.move = move;
    }
}
