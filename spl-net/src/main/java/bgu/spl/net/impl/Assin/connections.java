package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.bidi.Connections;

import java.util.Map;

public class connections implements Connections {

    private Map <ClientDetails, Integer> ClientsIds;



    @Override
    public boolean send(int connectionId, Object msg) {
        return false;
    }

    @Override
    public void broadcast(Object msg) {

    }

    @Override
    public void disconnect(int connectionId) {

    }
}
