package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.Messages;
import bgu.spl.net.impl.Assin.Messages.ErrorMessage;
import bgu.spl.net.impl.Assin.Messages.RegisterMessage;

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
        if(opcode == 0) {
            switch (opcode) {
                case 1: {
                    register((RegisterMessage) message);
                }
                case 2:

            }
        }


    }

    @Override
    public boolean shouldTerminate() {
        return shouldterminate;
    }


    public void register(RegisterMessage message) {
        if(database.isregister(message,clientid)) {
            ErrorMessage errorMessage = new ErrorMessage((short) 11,(short) 1);
            connections.send(clientid,errorMessage);
        }
        else {

            if(!database.isUsernameAvilable(message,clientid)) {
                database.getClientsIds().get(clientid).setUsername(message.getUsername());
                database.getClientsIds().get(clientid).setBirthday(message.getBirthday());
                database.getClientsIds().get(clientid).setPassword(message.getPassword());
                database.getClientsIds().get(clientid).setIsregistered(true);
                database.Addusername(message.getUsername(),database.getClientsIds().get(clientid));
                //send ACK
            }
            else {
                if(database.isLogedin(message,clientid)) {
                    //send Error
                }
                else {
                    ClientDetails tmpclient = database.getUsernames().get(message.getUsername());
                    ClientDetails clientDetails = database.getClientsIds().get(clientid);
                    database.getClientsIds().get(clientid).setUsername(message.getUsername());
                    database.getClientsIds().get(clientid).setBirthday(message.getBirthday());
                    database.getClientsIds().get(clientid).setPassword(message.getPassword());
                    database.getClientsIds().get(clientid).setIsregistered(true);
                    while (!tmpclient.getMessages().isEmpty())
                        clientDetails.addMessageTobeSent(tmpclient.getMessages().removeFirst());
                    database.getUsernames().remove(message.getUsername());
                    database.getUsernames().put(message.getUsername(),clientDetails);
                    //send ACK
                }
            }
        }
    }

}


