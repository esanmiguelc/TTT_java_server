package tttjava.responders;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;

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
                        "<h1 class=\"text-center\">Start a New Game</h1>" +
                            "<div class=\"col-lg-4 col-lg-offset-4\">" +
                                "<form action=\"/game\" method=\"GET\">\n" +
                                    "<input type=\"hidden\" name=\"state\" value=\" \"></input>" +
                                    "<div class=\"form-group\">" +
                                        "<label for=\"firstPlayer\">Select First Player:</label>" +
                                        "<select class=\"form-control\" id=\"firstPlayer\" name=\"firstPlayer\">" +
                                            "<option value=\"1\">Human</option>" +
                                            "<option value=\"2\">Impossible Computer</option>" +
                                        "</select>" +
                                    "</div>" +
                                    "<div class=\"form-group\">" +
                                        "<label for=\"secondPlayer\">Select Second Player:</label>" +
                                        "<select class=\"form-control\" name=\"secondPlayer\" id=\"secondPlayer\">" +
                                            "<option value=\"1\">Human</option>" +
                                            "<option value=\"2\">Impossible Computer</option>" +
                                        "</select>" +
                                    "</div>" +
                                "<button type=\"submit\" class=\"btn btn-success\">Start game!</button>" +
                            "</form>" +
                        "</div>" +
                    "</body>" +
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
