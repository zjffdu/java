package com.zjffdu.tutorial.hadoop.rpc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configuration.IntegerRanges;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.ipc.RPC;

class PingRPCImpl implements PingRPC {
  public String ping(String msg) {
    return "pong=" + msg;
  }
}

public class RPCExample {
  public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
    InetSocketAddress addr = new InetSocketAddress(0);
    Configuration conf=new Configuration();
    conf.addResource(new Path("conf/a.xml"));
    IntegerRanges range=conf.getRange("port.range", "");
    System.out.println(range.isIncluded(1000));
    String rangeStr=conf.get("port.range");
    
    final RPC.Server server =
        new RPC.Builder(conf).setInstance(new PingRPCImpl())
            .setProtocol(PingRPC.class)
            .setBindAddress(addr.getHostName())
//            .setPort(addr.getPort())
            .setNumHandlers(10)
            .setVerbose(false)
            .setPortRangeConfig("port.range")
            .build();
    server.start();
    InetSocketAddress address = server.getListenerAddress();
    System.out.println(address);
  }
}
