package bgu.spl.net.impl.echo;

import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.Server;

public class Main {

    public static void main(String[] args) {
        Server.reactor(5,7777,() -> new EchoProtocol(), () -> new LineMessageEncoderDecoder()).serve();
    }
}
