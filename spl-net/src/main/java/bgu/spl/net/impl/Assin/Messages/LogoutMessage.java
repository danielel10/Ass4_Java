package bgu.spl.net.impl.Assin.Messages;

public class LogoutMessage {

    private short opcode;

    public LogoutMessage(short opcode) {
        this.opcode = opcode;
    }

    public short getOpcode() {
        return opcode;
    }
}
