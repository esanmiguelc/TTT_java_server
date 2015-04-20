package tttjava;

import javaserver.Requests.HttpRequest;
import javaserver.Requests.HttpRequestParser;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import org.junit.Before;
import org.junit.Test;
import tttjava.responders.GameSetupResponder;
import tttjava.responders.MakeMoveResponder;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MakeMoveResponderTest {

    @Before
    public void setUp() throws Exception {
        CurrentGame.getInstance().resetGame();
    }

    @Test
    public void testMakesAMoveOnGame() {
        String unParsedRequest = "POST /new_game HTTP/1.1\r\n" +
                "\r\n" +
                "firstPlayer=1&secondPlayer=2\r\n";
        HttpRequest gameSetupRequest = new HttpRequestParser(unParsedRequest).createRequest();
        GameSetupResponder gameSetupResponder = new GameSetupResponder();
        Route newGameRoute = new Route("/new_game", false, false, new HashMap<>());
        newGameRoute.setCurrentParams(gameSetupRequest.getParams());
        gameSetupResponder.execute(newGameRoute, gameSetupRequest);

        String nonParsedRequest = "POST /make_move HTTP/1.1\r\n" +
                "\r\n" +
                "move=0\r\n";
        HttpRequest request = new HttpRequestParser(nonParsedRequest).createRequest();
        Responder responder = new MakeMoveResponder();
        Route route = new Route("/make_move", false, false, new HashMap<>());
        route.setCurrentParams(request.getParams());
        responder.execute(route, request);
        assertThat(CurrentGame.getInstance().getState().positionAvailable(0), is(equalTo(false)));
    }
}
