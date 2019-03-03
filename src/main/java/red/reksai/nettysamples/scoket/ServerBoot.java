package red.reksai.nettysamples.scoket;

/**
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-02-27 10:47
 * @since :
 */


public class ServerBoot {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }
}
