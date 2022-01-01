package bgu.spl.net.impl.Assin;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.bidi.Messages;
import bgu.spl.net.impl.Assin.Messages.BlockMessage;
import bgu.spl.net.impl.Assin.Messages.RegisterMessage;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
    private int len = 0;

    @Override
    public Messages decodeNextByte(byte nextByte) {
        if (nextByte == ';') {
            String strmsg = popString();
            char opcode = strmsg.charAt(0);
            if (opcode == '1') {
                short opcodereal = 12;
                String username = strmsg.substring(2,strmsg.length()-1);
                BlockMessage blockMessage = new BlockMessage(opcodereal,username);
                return blockMessage;
            }
            else {
                opcode = strmsg.charAt(1);
                switch (opcode) {
                    case '1': {
                        String username = strmsg.substring(2,strmsg.indexOf('0'));
                        strmsg = strmsg.substring(strmsg.indexOf('0') + 1);
                        String password = strmsg.substring(0,strmsg.indexOf('-')-3);
                        strmsg = strmsg.substring(strmsg.indexOf('0') + 1);
                        String birthday = strmsg.substring(0,strmsg.indexOf(';'));
                        RegisterMessage registerMessage = new RegisterMessage((short) 1,username,password,birthday);
                        return registerMessage;

                    }
                }
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
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    private String popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        short opcode = (short)((bytes[0] & 0xff) << 8);
        opcode += (short)(bytes[1] & 0xff);
        String result = String.valueOf(opcode);
        String messagepart = new String(bytes, 2, len, StandardCharsets.UTF_8);
        result = result + messagepart;
        len = 0;
        return result;
    }
}
