package com.java.netty.spring.protocol.message;

import lombok.Data;

import static com.java.netty.spring.protocol.message.command.Command.HEARTBEAT_REQUEST;


/**
 * @author xuweizhi
 */
@Data
public class HeartbeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
