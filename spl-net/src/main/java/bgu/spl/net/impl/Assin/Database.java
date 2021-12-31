package bgu.spl.net.impl.Assin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private Map<Integer, ClientDetails> ClientsIds;
    private Map <ClientDetails,connectionHandler> ClientsHandlers;

    private static Database instance = null;

    private Database() {
        ClientsIds = new ConcurrentHashMap<>();
        ClientsHandlers = new ConcurrentHashMap<>();

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

    public boolean register(int clientId,String clientDetails) {
        ClientDetails clientDetails = ClientsIds.get(clientId);
        if(clientDetails != null) {
            return false;
        }
        else {
            ClientDetails client = new ClientDetails()
        }
    }
}
