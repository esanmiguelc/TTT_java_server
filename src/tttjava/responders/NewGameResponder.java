package tttjava.responders;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import tictactoe.participants.ParticipantFactory;

import java.util.ArrayList;
import java.util.List;

public class NewGameResponder implements Responder {
    @Override
    public String contentBody() {
        String content = "";
        content += "<html>" +
                    "<head>" +
                        "<link href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\" rel=\"stylesheet\">" +
                    "</head>" +
                    "<body class=\"container\">" +
                        "<br>" +
                        "<br>" +
                        "<br>" +
                        "<h1 class=\"text-center\">Start a New Game</h1>\n" +
                            "<div class=\"col-lg-4 col-lg-offset-4\">\n" +
                                "<form action=\"/new_game\" method=\"POST\">\n" +
                                    "<div class=\"form-group\">\n" +
                                        "<label for=\"firstPlayer\">Select First Player:</label>\n" +
                                        "<select class=\"form-control\" id=\"firstPlayer\" name=\"firstPlayer\">\n" +
                                            "<option value=\"1\">Human</option>\n" +
                                        "</select>\n" +
                                    "</div>\n" +
                                    "<div class=\"form-group\">\n" +
                                        "<label for=\"secondPlayer\">Select Second Player:</label>\n" +
                                        "<select class=\"form-control\" name=\"secondPlayer\" id=\"secondPlayer\">\n" +
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
        return new ArrayList<>();
    }

    @Override
    public Responder execute(Route route, Request request) {
        return this;
    }
}
