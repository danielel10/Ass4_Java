package bgu.spl.net.impl.Assin;

public class MessageswaitingToBesent {

    private short opcode;
    private short typeofmessage;
    private String Postinguser;
    private String content;

    public MessageswaitingToBesent(short opcode, short typeofmessage, String postinguser, String content) {
        this.opcode = opcode;
        this.typeofmessage = typeofmessage;
        Postinguser = postinguser;
        this.content = content;
    }

    public short getOpcode() {
        return opcode;
    }

    public short getTypeofmessage() {
        return typeofmessage;
    }

    public String getPostinguser() {
        return Postinguser;
    }

    public String getContent() {
        return content;
    }
}
