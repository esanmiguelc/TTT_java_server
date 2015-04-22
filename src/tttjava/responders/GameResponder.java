package tttjava.responders;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import org.omg.CORBA.Current;
import tictactoe.gamestate.GameState;
import tttjava.CurrentGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameResponder implements Responder {

    @Override
    public String contentBody() {
        String html = "<html>\n" +
                "  <head>\n" +
                "    <link href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "<link href=\"game.css\" rel=\"stylesheet\">" +
                "  </head>\n" +
                "  <body class=\"container\">\n" +
                "    <h1 class=\"text-center\">Welcome To a game of TicTacToe</h1>\n";
        GameState state = CurrentGame.getInstance().getState();
        for (int position = 0; position < 9; position++) {
            html += "<div class=\"col-xs-4 slot slot-" + position + "\">\n";
            if (state.positionAvailable(position)) {
                html += "<form action=\"/\" method=\"POST\">\n" +
                        "    <input type=\"hidden\" name=\"move\" value=\"" + position + "\">\n" +
                        "    <button type=\"submit\"></button>\n" +
                        "</form>";
            } else {
                String mark = state.getParticipantAtPosition(position).getMark();
                html += "<span>" + mark + "</span>";
            }
            html += "</div>";
        }
        html += "<a href=\"new_game\">Restart Game</a>";
        html += "</body>\n" +
                "</html>\n";
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
        if (CurrentGame.getInstance().getRules().gameOver()) {
            return Collections.singletonList("Refresh: 0; url=/game_over");
        }
        return new ArrayList<>();
    }

    @Override
    public Responder execute(Route route, Request request) {
        return this;
    }
}
