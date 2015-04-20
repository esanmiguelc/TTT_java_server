package tttjava.responders;

import javaserver.Requests.Request;
import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import tttjava.CurrentGame;

import java.util.Arrays;
import java.util.List;

public class MakeMoveResponder implements Responder {
    @Override
    public String contentBody() {
        return "";
    }

    @Override
    public String statusCode() {
        return "200 OK";
    }

    @Override
    public String httpMethod() {
        return "POST";
    }

    @Override
    public List<String> additionalHeaders() {
        return Arrays.asList("Refresh: 0; url=/game");
    }

    @Override
    public Responder execute(Route route, Request request) {
        CurrentGame.getInstance().getState().placeNextMove(Integer.valueOf(request.getParams().get("move")));
        return this;
    }
}
