package tttjava;

import javaserver.Requests.Logger;
import javaserver.Runner.ServerRunner;
import javaserver.config.ServerConfig;
import tttjava.TicTacToeRoutes;

class TTTJava {
    public static void main(String[] args) throws Exception {
        Logger logger = new Logger();
        ServerConfig config = new ServerConfig(args);
        ServerRunner server = new ServerRunner(config, logger);
        TicTacToeRoutes.config();

        server.run();
    }
}

