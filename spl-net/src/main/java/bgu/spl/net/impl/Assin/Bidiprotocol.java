package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;

public class Bidiprotocol implements BidiMessagingProtocol<String> {

    private boolean shouldterminate;
    private Connections connections;
    private int clientid;
    private Database database;
    @Override
    public void start(int connectionId, Connections<String> connections) {
        clientid = connectionId;
        this.connections = connections;
        database = Database.getInstance();
    }


    @Override
    public void process(String message) {
        char charArray[] = message.toCharArray();
        short opcode = (short) charArray[0];
        if(opcode == 0) {
            opcode = (short)charArray[1];
            switch (opcode) {
                case 1: {


                }

            }
        }


    }

    @Override
    public boolean shouldTerminate() {
        return shouldterminate;
    }
}
