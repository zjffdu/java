package com.zjffdu.tutorial.mockito;

import com.zjffdu.tutorial.mockito.Service.Message;

public class Person {

  private Service service;

  public Person(Service service) {
    this.service = service;
  }

  public void walk() {
    this.service.handle(new Message("walk"));
  }

}
