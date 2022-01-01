package bgu.spl.net.impl.Assin.Messages;

public class StatMessage {

    private short opcode;
    private String ListOfUsernames;

    public StatMessage(short opcode, String listOfUsernames) {
        this.opcode = opcode;
        ListOfUsernames = listOfUsernames;
    }

    public short getOpcode() {
        return opcode;
    }

    public String getListOfUsernames() {
        return ListOfUsernames;
    }
}
