package bgu.spl.net.impl.echo;

import bgu.spl.net.impl.Assin.Bidiprotocol;
import bgu.spl.net.impl.Assin.EncDec;
import bgu.spl.net.srv.BaseServer;
import bgu.spl.net.srv.Server;

public class Main {

    public static void main(String[] args) {
//        Server.reactor(5,7777,() -> new Bidiprotocol(), () -> new EncDec()).serve();
        Server.threadPerClient(7777,() -> new Bidiprotocol(),() -> new EncDec()).serve();
    }
}
