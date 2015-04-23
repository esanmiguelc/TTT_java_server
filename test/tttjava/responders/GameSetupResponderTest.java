package tttjava.responders;

import javaserver.Requests.HttpRequest;
import javaserver.Requests.HttpRequestParser;
import javaserver.Routes.Route;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.portable.ResponseHandler;
import tictactoe.boards.TicTacToeBoard;
import tictactoe.games.TicTacToeGame;
import tictactoe.gamestate.GameState;
import tictactoe.participants.Participant;
import tictactoe.participants.ParticipantFactory;
import tictactoe.participants.computer.ImpossibleComputer;
import tictactoe.participants.human.HumanParticipant;
import tictactoe.rules.TicTacToeRules;
import tictactoe.ui.CommandLineUI;
import tttjava.CurrentGame;
import tttjava.responders.GameSetupResponder;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameSetupResponderTest {
    @Before
    public void setUp() throws Exception {
        CurrentGame.getInstance().resetGame();
    }

    @Test
    public void testCreatesAPlayerFromParams() {
        String nonParsedRequest = "GET /new_game HTTP/1.1\r\n" +
                "\r\n" +
                "firstPlayer=1&secondPlayer=1\r\n";
        HttpRequest request = new HttpRequestParser(nonParsedRequest).createRequest();
        String[] args = new String[0];
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard(3);
        Participant player = new ParticipantFactory(Integer.valueOf(request.getParams().get("firstPlayer")),
                new CommandLineUI(System.out, System.in, args),
                ticTacToeBoard,
                new TicTacToeRules(ticTacToeBoard),
                "X",
                new GameState(ticTacToeBoard)).getPlayer();
        assertThat(player, is(instanceOf(HumanParticipant.class)));
    }

    @Test
    public void testCreatesAComputerFromParams() {
        String nonParsedRequest = "GET /new_game HTTP/1.1\r\n" +
                "\r\n" +
                "firstPlayer=1&secondPlayer=2\r\n";
        HttpRequest request = new HttpRequestParser(nonParsedRequest).createRequest();
        String[] args = new String[0];
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard(3);
        Participant player = new ParticipantFactory(Integer.valueOf(request.getParams().get("secondPlayer")),
                new CommandLineUI(System.out, System.in, args),
                ticTacToeBoard,
                new TicTacToeRules(ticTacToeBoard),
                "X",
                new GameState(ticTacToeBoard)).getPlayer();
        assertThat(player, is(instanceOf(ImpossibleComputer.class)));
    }


    @Test
    public void testSetsUpAGameToBePlayed() {
        String nonParsedRequest = "POST /new_game HTTP/1.1\r\n" +
                "\r\n" +
                "firstPlayer=1&secondPlayer=2\r\n";
        HttpRequest request = new HttpRequestParser(nonParsedRequest).createRequest();
        GameSetupResponder responder = new GameSetupResponder();
        Route route = new Route("/new_game", false, false, new HashMap<>());
        route.setCurrentParams(request.getParams());
        responder.execute(route, request);
        assertThat(CurrentGame.getInstance().getGame(), is(instanceOf(TicTacToeGame.class)));
    }
}
