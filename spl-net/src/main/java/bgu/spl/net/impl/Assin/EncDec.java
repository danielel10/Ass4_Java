package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.bidi.Messages;
import bgu.spl.net.impl.Assin.Messages.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;

public class EncDec implements MessageEncoderDecoder<Messages> {

    //    @Override
//    public Messages decodeNextByte(byte nextByte) {
//        return null;
//    }
//
//    @Override
//    public byte[] encode(Messages message) {
//        return new byte[0];
//    }
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private byte[] opbyte = new byte[1 << 10];
    private int len = 0;
    LinkedList<String> msg = new LinkedList<>();
    private int count = 0;

    @Override
    public Messages decodeNextByte(byte nextByte) {
        if (nextByte == ';') {
            short opcode = pooOpcode();
            switch (opcode) {
                case 1: {
                    String username = msg.removeFirst();
                    String password = msg.removeFirst();
                    String birthday = msg.removeFirst();
                    RegisterMessage registerMessage = new RegisterMessage(opcode, username, password, birthday);
                    msg.clear();
                    return registerMessage;

                }
                case 2: {
                    String username = msg.removeFirst();
                    String password = msg.removeFirst();
                    String captcha = msg.removeFirst();
                    byte[] test = captcha.getBytes();
                    short captchas;
                    if (test[1] == 49) {
                        captchas = 1;
                    } else
                        captchas = 0;
                    msg.clear();
                    return new LoginMessage(opcode, username, password, captchas);

                }
                case 3: {
                    msg.clear();
                    return new LogoutMessage(opcode);
                }
                case 4: {
                    String followUnfollow = msg.removeFirst();
                    String username = msg.removeFirst();
                    byte[] test = followUnfollow.getBytes();
                    short followUnfollows;
                    if (test[0] == 49) {
                        followUnfollows = 1;
                    } else
                        followUnfollows = 0;
                    msg.clear();
                    return new FollowMessage(opcode, followUnfollows, username);
                }
                case 5: {
                    String content = msg.removeFirst();
                    LinkedList<String> users = new LinkedList<>();

                    while (!msg.getFirst().equals("0")) {
                        String str = msg.removeFirst();
                        System.out.println(str);
                        if (str.charAt(0) == '@') {
                            users.add(str.substring(1));
                        }
                        content = content + " " + str;
                    }
                    msg.clear();
                    return new PostMessage(opcode, content, users);
                }
                case 6: {
                    String username = msg.removeFirst();
                    String content = msg.removeFirst();
                    String Sending_Date_And_Time = msg.removeFirst();
                    msg.clear();
                    return new PMMessage(opcode, username, content, Sending_Date_And_Time);
                }
                case 7: {
                    msg.clear();
                    return new LogStatMessage(opcode);
                }
                case 8: {
                    String usernamelist = msg.removeFirst();
                    msg.clear();
                    return new StatMessage(opcode, usernamelist.split("\\|"));
                }
                case 12: {
                    String username = msg.removeFirst();
                    BlockMessage blockMessage = new BlockMessage(opcode, username);
                    msg.clear();
                    return blockMessage;
                }
            }
        }
        if (nextByte == 0) {
            if (count > 1) {
                String word = popString();
                msg.add(word);
            }

        }

        pushByte(nextByte);
        return null; //not a line yet
    }

    @Override
    public byte[] encode(Messages message) {
        return (message + "\n").getBytes(); //uses utf8 by default
    }

    private void pushByte(byte nextByte) {
        if (count < 2) {
            opbyte[count++] = nextByte;
        } else {
            if (nextByte != 0) {
                if (len >= bytes.length) {
                    bytes = Arrays.copyOf(bytes, len * 2);
                }

                bytes[len++] = nextByte;
            }
        }

    }

    private String popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        return result;
    }

    private short pooOpcode() {
        short opcode = (short) ((opbyte[0] & 0xff) << 8);
        opcode += (short) (opbyte[1] & 0xff);
        count = 0;
        return opcode;

    }
}
