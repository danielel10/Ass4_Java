package bgu.spl.net.impl.Assin.Messages;

import bgu.spl.net.api.bidi.Messages;

import java.util.LinkedList;

public class StatMessage implements Messages {

    private short opcode;
    private String[] ListOfUsernames;

    public StatMessage(short opcode, String[] listOfUsernames) {
        this.opcode = opcode;
        ListOfUsernames = listOfUsernames;
    }

    public short getOpcode() {
        return opcode;
    }

    public String[] getListOfUsernames() {
        return ListOfUsernames;
    }
}
