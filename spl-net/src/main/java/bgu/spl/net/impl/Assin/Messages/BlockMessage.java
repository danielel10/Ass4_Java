package bgu.spl.net.impl.Assin.Messages;

public class BlockMessage {

    private short opcode;
    private String username;

    public BlockMessage(short opcode, String username) {
        this.opcode = opcode;
        this.username = username;
    }

    public short getOpcode() {
        return opcode;
    }

    public String getUsername() {
        return username;
    }
}
