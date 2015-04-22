package tttjava;

import javaserver.Requests.Logger;
import javaserver.Runner.ServerRunner;
import javaserver.config.ServerConfig;

class TTTJava {
    public static void main(String[] args) throws Exception {
        Logger logger = new Logger();
        ServerConfig config = new ServerConfig(args);
        System.out.println(config.getDirectory());
        ServerRunner server = new ServerRunner(config, logger);
        TicTacToeRoutes.config();

        server.run();
    }
}

