package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.bidi.Messages;
import bgu.spl.net.impl.Assin.Messages.RegisterMessage;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private Map<Integer, ClientDetails> ClientsIds;
    private Map <ClientDetails,connectionHandler> ClientsHandlers;
    private Map <String,ClientDetails> usernames;

    private static Database instance = null;

    private Database() {
        ClientsIds = new ConcurrentHashMap<>();
        ClientsHandlers = new ConcurrentHashMap<>();
        usernames = new ConcurrentHashMap<>();

    }

    public static synchronized Database getInstance() {
        if(instance == null)
            instance = new Database();
        return instance;
    }

    public Map<Integer, ClientDetails> getClientsIds() {
        return ClientsIds;
    }

    public Map<ClientDetails, connectionHandler> getClientsHandlers() {
        return ClientsHandlers;
    }

    public boolean isregister(RegisterMessage messages, int clientId) {
        return true;
    }

    public boolean isUsernameAvilable(RegisterMessage message, int clientid){
        return true;
    }

    public boolean isLogedin(RegisterMessage message, int clientid) {
        return  true;
    }

    public void Addusername(String username, ClientDetails clientDetails) {
        usernames.put(username,clientDetails);
    }

    public Map<String, ClientDetails> getUsernames() {
        return usernames;
    }
}
