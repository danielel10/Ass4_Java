package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;

public class Bidiprotocol implements BidiMessagingProtocol {
    @Override
    public void start(int connectionId, Connections connections) {

    }

    @Override
    public void process(Object message) {

    }

    @Override
    public boolean shouldTerminate() {
        return false;
    }
}
