package tttjava.responders;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import org.omg.CORBA.Current;
import tictactoe.gamestate.GameState;
import tttjava.CurrentGame;
import tttjava.WebGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameResponder implements Responder {

    private WebGame webGame;

    @Override
    public String contentBody() {

        String html = "<html>" +
                "  <head>" +
                "    <link href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\" rel=\"stylesheet\">" +
                    "<link href=\"game.css\" rel=\"stylesheet\">" +
                "  </head>" +
                "  <body class=\"container\">" +
                "    <h1 class=\"text-center\">Welcome To a game of TicTacToe</h1>";
        html = getGrid(html);
        html += "<a href=\"new_game\">Restart Game</a>";
        html += "</body>\n" +
                "</html>\n";
        return html;
    }
    private String getGrid(String html) {
        for (int position = 0; position < 9; position++) {
            html += "<div class=\"col-xs-4 slot slot-" + position + "\">";
            if (webGame.positionAvailable(position)) {
                html += "<form action=\"/game\" method=\"GET\">\n" +
                        "    <input type=\"hidden\" name=\"state\" value=\"" + webGame.playedMoves() + position + "\">" +
                        "    <input type=\"hidden\" name=\"firstPlayer\" value=\"1\">" +
                        "    <input type=\"hidden\" name=\"secondPlayer\" value=\"1\">" +
                        "    <button type=\"submit\"></button>\n" +
                        "</form>";
            } else {
                String mark = webGame.getParticipantAtPosition(position).getMark();
                html += "<span>" + mark + "</span>";
            }
            html += "</div>";
        }
        return html;
    }

    @Override
    public String statusCode() {
        return "200 OK";
    }

    @Override
    public String httpMethod() {
        return "GET";
    }

    @Override
    public List<String> additionalHeaders() {
        if (webGame.gameOver()) {
            return Arrays.asList("Refresh: 1; url=/game_over?result=" + webGame.getResultMark());
        }
        return new ArrayList<>();
    }

    @Override
    public Responder execute(Route route, Request request) {
        this.webGame = new WebGame();
        webGame.setFirstParticipant(request.getParams().get("firstPlayer"));
        webGame.setSecondParticipant(request.getParams().get("secondPlayer"));
        webGame.setPlayedMoves(request.getParams().get("state"));
        webGame.makeState();
        return this;
    }
}
