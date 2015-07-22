package com.zjffdu.tutorial.hadoop.rpc;

import org.apache.hadoop.ipc.ProtocolInfo;

@ProtocolInfo(protocolName = "ping", protocolVersion = 1)
interface PingRPC {
    String ping(String msg);
}
