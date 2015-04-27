package tttjava;

import javaserver.Requests.HttpRequest;
import javaserver.Requests.Request;
import org.junit.Test;
import tictactoe.participants.computer.ImpossibleComputer;
import tictactoe.participants.human.HumanParticipant;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class WebGameTest {
    @Test
    public void testIsANewGame() {
        WebGame game = new WebGame();
        assertThat(game.isNew(), is(equalTo(true)));
    }

    @Test
    public void testIsNotANewGame() {
        WebGame game = new WebGame();
        game.addMove("1");
        assertThat(game.isNew(), is(equalTo(false)));
    }

    @Test
    public void testHoldsACurrentState() {
        WebGame game = new WebGame();
        assertThat(game.playedMoves(), is(equalTo(" ")));
    }

    @Test
    public void testAddsMoveOneToCurrentState() {
        Request request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<>(),new HashMap<String, String>(){{
            put("move", "1");
        }});
        WebGame game = new WebGame();
        game.addMove(request.getParams().get("move"));
        assertThat(game.playedMoves(), is(equalTo("1")));
    }

    @Test
    public void testAddsMoveTwoToCurrentState() {
        Request request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<>(),new HashMap<String, String>(){{
            put("move", "2");
        }});
        WebGame game = new WebGame();
        game.addMove(request.getParams().get("move"));
        assertThat(game.playedMoves(), is(equalTo("2")));
    }

    @Test
    public void testTwoMovesAreInCurrentState() {
        Request request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<>(),new HashMap<String, String>(){{
            put("state", "12");
        }});
        WebGame game = new WebGame();
        game.setPlayedMoves(request.getParams().get("state"));
        assertThat(game.playedMoves(), is(equalTo("12")));
    }

    @Test
    public void testHasPreviousStateAndHasMove() {
        Request request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<>(),new HashMap<String, String>(){{
            put("state", "12");
            put("move", "3");
        }});
        WebGame game = new WebGame();
        game.setPlayedMoves(request.getParams().get("state"));
        game.addMove(request.getParams().get("move"));
        assertThat(game.playedMoves(), is(equalTo("123")));
    }

    @Test
    public void testSetsTheFirstParticipantToHuman() {
        Request request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<>(),new HashMap<String, String>(){{
            put("state", "12");
            put("move", "3");
            put("firstParticipant", "1");
        }});
        WebGame game = new WebGame();
        game.setPlayedMoves(request.getParams().get("state"));
        game.setFirstParticipant(request.getParams().get("firstParticipant"));
        game.addMove(request.getParams().get("move"));
        assertThat(game.firstParticipant(), is(instanceOf(HumanParticipant.class)));
    }

    @Test
    public void testSetsTheFirstParticipantToComputer() {
        Request request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<>(),new HashMap<String, String>(){{
            put("firstParticipant", "2");
        }});
        WebGame game = new WebGame();
        game.setFirstParticipant(request.getParams().get("firstParticipant"));
        assertThat(game.firstParticipant(), is(instanceOf(ImpossibleComputer.class)));
    }

    @Test
    public void testSetsTheSecondParticipantToComputer() {
        Request request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<>(),new HashMap<String, String>(){{
            put("secondParticipant", "2");
        }});
        WebGame game = new WebGame();
        game.setSecondParticipant(request.getParams().get("secondParticipant"));
        assertThat(game.secondParticipant(), is(instanceOf(ImpossibleComputer.class)));
    }

    @Test
    public void testGameIsOver() {
        Request request = new HttpRequest("GET", "/", "HTTP/1.1", new HashMap<>(),new HashMap<String, String>(){{
            put("state", "04152");
            put("firstParticipant", "1");
            put("secondParticipant", "2");
        }});
        WebGame game = new WebGame();
        game.setFirstParticipant(request.getParams().get("firstParticipant"));
        game.setSecondParticipant(request.getParams().get("secondParticipant"));
        game.setPlayedMoves(request.getParams().get("state"));
        game.makeState();
        assertThat(game.gameOver(), is(equalTo(true)));
    }
}
