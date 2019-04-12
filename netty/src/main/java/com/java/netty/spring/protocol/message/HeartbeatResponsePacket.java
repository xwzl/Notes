package com.java.netty.spring.protocol.message;


import com.java.netty.spring.protocol.message.command.Command;

/**
 * @author xuweizhi
 */
public class HeartbeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
