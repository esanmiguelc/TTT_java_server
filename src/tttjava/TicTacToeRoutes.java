package tttjava;

import javaserver.Responses.Responders.Responder;
import javaserver.Routes.Route;
import javaserver.Routes.RoutesRegistrar;
import tttjava.responders.GameResponder;
import tttjava.responders.MakeMoveResponder;
import tttjava.responders.NewGameResponder;
import tttjava.responders.GameSetupResponder;

import java.util.LinkedHashMap;

public class TicTacToeRoutes {

    public static void config() {
        RoutesRegistrar.getInstance().registerRoute(new Route("/new_game", false, false, new LinkedHashMap<String, Responder>() {{
            put("POST", new GameSetupResponder());
            put("GET", new NewGameResponder());
        }}));
        RoutesRegistrar.getInstance().registerRoute(new Route("/game", false, false, new LinkedHashMap<String, Responder>() {{
            put("GET", new GameResponder());
        }}));
        RoutesRegistrar.getInstance().registerRoute(new Route("/make_move", false, false, new LinkedHashMap<String, Responder>() {{
            put("POST", new MakeMoveResponder());
        }}));
    }
}
