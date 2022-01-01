package bgu.spl.net.impl.Assin.Messages;

public class PostMessage {

    private short opcode;
    private String content;

    public PostMessage(short opcode, String content) {
        this.opcode = opcode;
        this.content = content;
    }

    public short getOpcode() {
        return opcode;
    }

    public String getContent() {
        return content;
    }
}
