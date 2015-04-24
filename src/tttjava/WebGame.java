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
    private GameState gameState = new GameState(board);
    private TicTacToeRules rules = new TicTacToeRules(board);
    private Integer participantType;
    private Integer secondParticipantType;

    public void addMove(String move) {
        playedMoves += move;
    }

    public String playedMoves() {
        String noWhiteSpace = playedMoves.replaceAll("[^0-9]", "");
        return (playedMoves.equals(" ")) ? " " : noWhiteSpace;
    }

    public void setPlayedMoves(String playedMoves) {
        this.playedMoves = playedMoves;
    }

    public Participant firstParticipant() {
        return firstParticipant;
    }

    public void setFirstParticipant(String firstParticipant) {
        this.participantType = Integer.valueOf(firstParticipant);
        this.firstParticipant = new ParticipantFactory(participantType, null, board, rules, "X", gameState).getPlayer();
    }

    public void setSecondParticipant(String secondParticipant) {
        this.secondParticipantType = Integer.valueOf(secondParticipant);
        this.secondParticipant = new ParticipantFactory(secondParticipantType, null, board, rules, "O", gameState).getPlayer();
    }

    public Participant secondParticipant() {
        return secondParticipant;
    }

    public boolean gameOver() {
        return new TicTacToeRules(board).gameOver();
    }

    public void makeState() {
        gameState.setFirstParticipant(firstParticipant);
        gameState.setSecondParticipant(secondParticipant);
        Arrays.asList(playedMoves().split("")).stream()
                .filter((stringPosition) -> stringPosition.matches("[0-9]"))
                .forEach((position) -> gameState.placeNextMove(Integer.valueOf(position)));
        if (gameState.currentParticipant().getType().equals("Computer") && !rules.gameOver()) {
            int computerMove = gameState.currentParticipant().getMove();
            this.playedMoves += String.valueOf(computerMove);
            gameState.placeNextMove(computerMove);
        }
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

    public Integer getSecondParticipantType() {
        return secondParticipantType;
    }

    public Integer getFirstParticipantType() {
        return participantType;
    }
}
