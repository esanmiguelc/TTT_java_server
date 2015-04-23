package tttjava;

import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import javaserver.Routes.RoutesRegistrar;
import tttjava.responders.*;

import java.util.LinkedHashMap;

public class TicTacToeRoutes {

    public static void config() {
        RoutesRegistrar.getInstance().registerRoute(new Route("/", false, false, new LinkedHashMap<String, Responder>() {{
            put("GET", new SomeGameResponder());
            put("POST", new SomeGameResponder());
        }}));

        RoutesRegistrar.getInstance().registerRoute(new Route("/new_game", false, false, new LinkedHashMap<String, Responder>() {{
            put("GET", new NewGameResponder());
        }}));

        RoutesRegistrar.getInstance().registerRoute(new Route("/game", false, false, new LinkedHashMap<String, Responder>() {{
            put("GET", new GameResponder());
        }}));

        RoutesRegistrar.getInstance().registerRoute(new Route("/game_over", false, false, new LinkedHashMap<String, Responder>() {{
            put("GET", new GameOverResponder());
        }}));
    }
}
