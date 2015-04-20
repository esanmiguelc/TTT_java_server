package tttjava.responders;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import org.omg.CORBA.Current;
import tictactoe.gamestate.GameState;
import tttjava.CurrentGame;

import java.util.ArrayList;
import java.util.List;

public class GameResponder implements Responder {

    @Override
    public String contentBody() {
        String html = "<html>\n" +
                "  <head>\n" +
                "    <link href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "      .slot {\n" +
                "        height: 200px;\n" +
                "      }\n" +
                "      h1 {\n" +
                "        padding-bottom: 40px;\n" +
                "      }\n" +
                "      .slot-0 {\n" +
                "        border-right: 3px solid #f0f0f0;\n" +
                "        border-bottom: 3px solid #f0f0f0;\n" +
                "      }\n" +
                "      .slot-1 {\n" +
                "        border-right: 3px solid #f0f0f0;\n" +
                "        border-bottom: 3px solid #f0f0f0;\n" +
                "      }\n" +
                "      .slot-2 {\n" +
                "        border-bottom: 3px solid #f0f0f0;\n" +
                "      }\n" +
                "      .slot-3 {\n" +
                "        border-right: 3px solid #f0f0f0;\n" +
                "        border-bottom: 2px solid #f0f0f0;\n" +
                "      }\n" +
                "      .slot-4 {\n" +
                "        border-right: 3px solid #f0f0f0;\n" +
                "        border-bottom: 2px solid #f0f0f0;\n" +
                "      }\n" +
                "      .slot-5 {\n" +
                "        border-bottom: 3px solid #f0f0f0;\n" +
                "      }\n" +
                "      .slot-6 {\n" +
                "        border-right: 3px solid #f0f0f0;\n" +
                "      }\n" +
                "      .slot-7 {\n" +
                "        border-right: 3px solid #f0f0f0;\n" +
                "      }\n" +
                "      button {\n" +
                "        width:100%;\n" +
                "        height:100%;\n" +
                "        border:none;\n" +
                "        background-color:white;\n" +
                "      }" +
                "      span {\n" +
                "        font-size: 130px;\n" +
                "        display:block;\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"container\">\n" +
                "    <h1 class=\"text-center\">Welcome To a game of TicTacToe</h1>\n";
        GameState state = CurrentGame.getInstance().getState();
        for (int position : state.availableMoves()) {
            html += "<div class=\"col-xs-4 slot slot-" + position + "\">\n";
            state.positionAvailable(position);
            if (!state.positionAvailable(position)) {
                html += "<span>" + state.getParticipantAtPosition(position).getMark() + "</span>";
            } else {
                html += "<form action=\"/make_move\" method=\"POST\">\n" +
                        "    <input type=\"hidden\" name=\"move\" value=\"" + position + "\">\n" +
                        "    <button type=\"submit\"></button>\n" +
                        "</form>";
            }
            html += "</div>";
        }
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
        return new ArrayList<>();
    }

    @Override
    public Responder execute(Route route, Request request) {
        return this;
    }
}
