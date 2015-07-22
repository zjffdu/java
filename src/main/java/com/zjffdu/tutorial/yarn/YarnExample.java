package com.zjffdu.tutorial.yarn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.event.AsyncDispatcher;
import org.apache.hadoop.yarn.event.Event;
import org.apache.hadoop.yarn.event.EventHandler;

public class YarnExample {

  private static Log LOG = LogFactory.getLog(YarnExample.class);

  private static class MyEvent implements Event<MyEventType> {

    public MyEventType getType() {
      return MyEventType.Test;
    }

    public long getTimestamp() {
      return 0;
    }

  }

  private enum MyEventType {
    Test,
  }

  private static class MyEventHandler implements EventHandler<Event> {

    public void handle(Event event) {
      LOG.info(Thread.currentThread().getId());
    }

  }

  public static void main(String[] args) {


  }
}