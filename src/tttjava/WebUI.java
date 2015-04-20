package tttjava;

import tictactoe.boards.Board;
import tictactoe.participants.human.Errors;
import tictactoe.ui.UI;

public class WebUI implements UI {
    @Override
    public int numberInput() {
        return 0;
    }

    @Override
    public void askPlayerForTurn() {

    }

    @Override
    public void print(String s) {

    }

    @Override
    public void formatBoard(Board board) {

    }

    @Override
    public boolean askPlayAgain() {
        return false;
    }

    @Override
    public int getFirstParticipantType() {
        return 0;
    }

    @Override
    public int getSecondParticipantType() {
        return 0;
    }

    @Override
    public void records() {

    }

    @Override
    public void printWinner(String s) {

    }

    @Override
    public void printTie() {

    }

    @Override
    public void moveError(Errors errors, Board board) {

    }

    @Override
    public String getFirstParticipantMark() {
        return null;
    }

    @Override
    public String getSecondParticipantMark() {
        return null;
    }
}
