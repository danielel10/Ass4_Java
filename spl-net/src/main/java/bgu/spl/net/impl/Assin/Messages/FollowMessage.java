package bgu.spl.net.impl.Assin.Messages;

public class FollowMessage {

    private short opcode;
    private short follow; //0 for follow, 1 for unfollow
    private String username;

    public FollowMessage(short opcode, short follow, String username) {
        this.opcode = opcode;
        this.follow = follow;
        this.username = username;
    }

    public short getOpcode() {
        return opcode;
    }

    public short getFollow() {
        return follow;
    }

    public String getUsername() {
        return username;
    }
}
