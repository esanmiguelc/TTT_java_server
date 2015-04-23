package tttjava;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;

import java.util.Collections;
import java.util.List;

public class SomeGameResponder implements Responder {

    private WebGame game;

    @Override
    public String contentBody() {
        return game.isNew() ? setupPresenter() : gamePresenter();
    }

    private String gamePresenter() {
        String content = "<html>\n" +
                "  <head>\n" +
                "    <link href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "<link href=\"game.css\" rel=\"stylesheet\">" +
                "  </head>\n" +
                "  <body class=\"container\">\n" +
                "    <h1 class=\"text-center\">Welcome To a game of TicTacToe</h1>\n" +
                "<form action=\"/\" method=\"POST\">\n";
        for (int position = 0; position < 9; position++) {
            content += "<div class=\"col-xs-4 slot slot-" + position + "\">\n";
                content +=
"    <input type=\"hidden\" name=\"firstParticipant\" value=\"" + "1" + "\">\n" +
                        "    <input type=\"hidden\" name=\"secondParticipant\" value=\"" + "1" + "\">\n" +
                        "    <button type=\"submit\"></button>\n" +
                "</form>";
                content += "</div>";
            }
        content += "<a href=\"new_game\">Restart Game</a>";
        content += "</body>\n" +
                "</html>\n";
        return content;
    }

    private String setupPresenter() {
        String content = "<html>" +
                "<head>" +
                "<link href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\" rel=\"stylesheet\">" +
                "</head>" +
                "<body class=\"container\">" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<h1 class=\"text-center\">Start a New Game</h1>\n" +
                "<div class=\"col-lg-4 col-lg-offset-4\">\n" +
                "<form action=\"/\" method=\"POST\">\n" +
                "<input type=\"hidden\" name=\"state\" value=\"" + " " + "\">\n" +
                "<input type=\"hidden\" name=\"move\" value=\"" + " " + "\">\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"firstParticipant\">Select First Player:</label>\n" +
                "<select class=\"form-control\" id=\"firstParticipant\" name=\"firstParticipant\">\n" +
                "<option value=\"1\">Human</option>\n" +
                "</select>\n" +
                "</div>\n" +
                "<div class=\"form-group\">\n" +
                "<label for=\"secondParticipant\">Select Second Player:</label>\n" +
                "<select class=\"form-control\" name=\"secondParticipant\" id=\"secondParticipant\">\n" +
                "<option value=\"1\">Human</option>\n" +
                "</select>\n" +
                "</div>\n" +
                "<button type=\"submit\" class=\"btn btn-success\">Start game!</button>\n" +
                "</form>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        return content;
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
        return Collections.singletonList("Refresh: url=/");
    }

    @Override
    public Responder execute(Route route, Request request) {
        game = new WebGame();
        if (request.getParams().isEmpty()) {
            return this;
        }
        game.setFirstParticipant(request.getParams().get("firstParticipant"));
        game.setSecondParticipant(request.getParams().get("secondParticipant"));
        game.setPlayedMoves(request.getParams().get("state"));
        game.makeState();
        game.addMove(request.getParams().get("move"));
        route.resetParams();
        return this;
    }
}
