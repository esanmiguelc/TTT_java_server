package tttjava.responders;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import tttjava.CurrentGame;

import java.util.ArrayList;
import java.util.List;

public class GameOverResponder implements Responder {

    private String result;

    @Override
    public String contentBody() {
        String html = "<html>\n" +
                "  <head>\n" +
                "    <link href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "</head>";
        html += "<body class=\"container\">";
        if (result.equals("tie")) {
            html += "<h1 class=\"text-center\">Game Over!</h1><h2 class=\"text-center\">It's a tie!</h2>";
        } else {
            html += "<h1 class=\"text-center\">Game Over!</h1> <h2 class=\"text-center\">The results are in: " +
                    result +
                    " Wins! </h2>";
        }
        html += "<a class=\"center-block text-center\" href=\"/new_game\">click here to start a new game</a>";
        html += "</body>" +
                "</html>";
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
        this.result = request.getParams().get("result");
        return this;
    }
}
