package bgu.spl.net.impl.Assin.Messages;

public class LogStatMessage {

    private short opcode;

    public LogStatMessage(short opcode) {
        this.opcode = opcode;
    }

    public short getOpcode() {
        return opcode;
    }
}
