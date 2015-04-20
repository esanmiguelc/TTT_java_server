package tttjava.responders;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import tictactoe.boards.TicTacToeBoard;
import tictactoe.games.*;
import tictactoe.gamestate.GameState;
import tictactoe.gamestate.State;
import tictactoe.participants.Participant;
import tictactoe.participants.ParticipantFactory;
import tictactoe.rules.TicTacToeRules;
import tttjava.CurrentGame;
import tttjava.WebUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSetupResponder implements Responder {
    @Override
    public String contentBody() {
        return "<head>" +
                "</head>" +
                "<body>" +
                "Game Created " +
                "<a href=\"/game\">Start Playing</a>" +
                "</body>";
    }

    @Override
    public String statusCode() {
        return "200 OK";
    }

    @Override
    public String httpMethod() {
        return "POST";
    }

    @Override
    public List<String> additionalHeaders() {
        return Arrays.asList("Refresh: 0; url=/game");
    }

    @Override
    public Responder execute(Route route, Request request) {
        int size = 3;
        TicTacToeBoard board = new TicTacToeBoard(size);
        TicTacToeRules rules = new TicTacToeRules(board);
        GameState state = new GameState(board);
        Participant firstParticipant = new ParticipantFactory(Integer.valueOf(request.getParams().get("firstPlayer")), new WebUI(), board, rules, "X",state).getPlayer();
        Participant secondParticipant = new ParticipantFactory(Integer.valueOf(request.getParams().get("secondPlayer")), new WebUI(), board, rules, "O",state).getPlayer();
        state.setFirstParticipant(firstParticipant);
        state.setSecondParticipant(secondParticipant);
        TicTacToeGame game = new TicTacToeGameBuilder()
                .setTicTacToeBoard(board)
                .setRules(rules)
                .setGameState(state)
                .build();
        CurrentGame.getInstance().setState(state);
        CurrentGame.getInstance().setGame(game);
        return this;
    }
}
