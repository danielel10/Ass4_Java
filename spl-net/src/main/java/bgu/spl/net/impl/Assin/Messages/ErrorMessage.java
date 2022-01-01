package bgu.spl.net.impl.Assin.Messages;

public class ErrorMessage {

    private short opcode;
    private short messageOpcode;

    public ErrorMessage(short opcode, short messageOpcode) {
        this.opcode = opcode;
        this.messageOpcode = messageOpcode;
    }

    public short getOpcode() {
        return opcode;
    }

    public short getMessageOpcode() {
        return messageOpcode;
    }
}
