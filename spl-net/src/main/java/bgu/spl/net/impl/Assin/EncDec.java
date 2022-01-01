package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.bidi.Messages;
import bgu.spl.net.impl.Assin.Messages.BlockMessage;
import bgu.spl.net.impl.Assin.Messages.RegisterMessage;

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
            msg.add(popString());
            String strmsg = popString();
            switch (opcode) {
                case 1: {
                    String username = strmsg.substring(2, strmsg.indexOf('0'));
                    strmsg = strmsg.substring(strmsg.indexOf('0') + 1);
                    String password = strmsg.substring(0, strmsg.indexOf('-') - 3);
                    strmsg = strmsg.substring(strmsg.indexOf('0') + 1);
                    String birthday = strmsg.substring(0, strmsg.indexOf(';'));
                    RegisterMessage registerMessage = new RegisterMessage((short) 1, username, password, birthday);
                    msg.clear();
                    return registerMessage;

                }
                case 12: {
                    String username = strmsg.substring(2, strmsg.length() - 1);
                    BlockMessage blockMessage = new BlockMessage(opcode, username);
                    msg.clear();
                    return blockMessage;
                }
            }
        }
        if(nextByte == 0) {
            if(count > 1) {
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
        if(count < 2) {
            opbyte[count++] = nextByte;
        }
        else {
            if (len >= bytes.length) {
                bytes = Arrays.copyOf(bytes, len * 2);
            }

            bytes[len++] = nextByte;
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
