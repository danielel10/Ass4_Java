package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.bidi.Connections;


public class connections<T> implements Connections<T> {

    private Database database;

    private static connections instance = null;

    private connections(Database database) {

        database = Database.getInstance();
    }

    public static synchronized connections getInstance(Database database) {
        if(instance == null)
            instance = new connections(database);
        return instance;
    }


    @Override
    public boolean send(int connectionId, T msg) {
        ClientDetails clientDetails = database.getClientsIds().get(connectionId);
        database.getClientsHandlers().get(clientDetails).send(msg);
        return true;
    }

    @Override
    public void broadcast(T msg) {

    }

    @Override
    public void disconnect(int connectionId) {
        database.getClientsIds().get(connectionId).setIslogedin(false);
    }

}