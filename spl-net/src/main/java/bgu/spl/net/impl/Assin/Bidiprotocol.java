package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.Messages;
import bgu.spl.net.impl.Assin.Messages.*;

public class Bidiprotocol implements BidiMessagingProtocol<Messages> {

    private boolean shouldterminate;
    private Connections connections;
    private int clientid;
    private Database database;

    @Override
    public void start(int connectionId, Connections<Messages> connections) {
        clientid = connectionId;
        this.connections = connections;
        database = Database.getInstance();
    }


    @Override
    public void process(Messages message) {
        short opcode = message.getOpcode();
        switch (opcode) {
            case 1: {
                register((RegisterMessage) message);
                break;
            }
            case 2: {
                Login((LoginMessage) message, clientid);
                break;
            }
            case 3: {
                Logout((LogoutMessage) message, clientid);
                break;
            }
            case 4: {

            }

        }


    }

    @Override
    public boolean shouldTerminate() {
        return shouldterminate;
    }


    public void register(RegisterMessage message) {
        if (database.isregister(clientid)) {
            ErrorMessage errorMessage = new ErrorMessage((short) 11, (short) 1);
            connections.send(clientid, errorMessage);
        } else {

            if (!database.isUsernameAvilable(message.getUsername(), clientid)) {
                database.getClientsIds().get(clientid).setUsername(message.getUsername());
                database.getClientsIds().get(clientid).setBirthday(message.getBirthday());
                database.getClientsIds().get(clientid).setPassword(message.getPassword());
                database.getClientsIds().get(clientid).setIsregistered(true);
                database.Addusername(message.getUsername(), database.getClientsIds().get(clientid));
                AckMessage ackMessage = new AckMessage((short) 10, (short) 1, null, (short) 0, (short) 0, (short) 0, (short) 0);
                connections.send(clientid, ackMessage);
            } else {
                if (database.isLogedin(message.getUsername(), clientid)) {
                    connections.send(clientid, new ErrorMessage((short) 11, (short) 1));
                } else {
                    ClientDetails tmpclient = database.getUsernames().get(message.getUsername());
                    ClientDetails clientDetails = database.getClientsIds().get(clientid);
                    database.getClientsIds().get(clientid).setUsername(message.getUsername());
                    database.getClientsIds().get(clientid).setBirthday(message.getBirthday());
                    database.getClientsIds().get(clientid).setPassword(message.getPassword());
                    database.getClientsIds().get(clientid).setIsregistered(true);
                    while (!tmpclient.getMessages().isEmpty())
                        clientDetails.addMessageTobeSent(tmpclient.getMessages().removeFirst());
                    database.getUsernames().remove(message.getUsername());
                    database.getUsernames().put(message.getUsername(), clientDetails);
                    AckMessage ackMessage = new AckMessage((short) 10, (short) 1, null, (short) 0, (short) 0, (short) 0, (short) 0);
                    connections.send(clientid, ackMessage);
                }
            }
        }
    }

    public void Login(LoginMessage loginMessage, int clientid) {
        if (database.isregister(clientid)) {
            if (!database.isLogedin(loginMessage.getUsername(), clientid)) {
                if (database.getClientsIds().get(clientid).getPassword().equals(loginMessage.getPassword())) {
                    AckMessage ackMessage = new AckMessage((short) 10, (short) 2, null, (short) 0, (short) 0, (short) 0, (short) 0);
                    connections.send(clientid, ackMessage);
                } else
                    connections.send(clientid, new ErrorMessage((short) 11, (short) 2));
            } else
                connections.send(clientid, new ErrorMessage((short) 11, (short) 2));

        } else
            connections.send(clientid, new ErrorMessage((short) 11, (short) 2));
    }

    public void Logout(LogoutMessage logoutMessage, int clientid) {
        if (database.isregister(clientid)) {
            if (database.isLogedin(null, clientid)) {
                AckMessage ackMessage = new AckMessage((short) 10, (short) 3, null, (short) 0, (short) 0, (short) 0, (short) 0);
                connections.send(clientid, ackMessage);
            } else
                connections.send(clientid, new ErrorMessage((short) 11, (short) 3));
        } else
            connections.send(clientid, new ErrorMessage((short) 11, (short) 3));

    }

}


