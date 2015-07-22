package com.zjffdu.tutorial.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.service.AbstractService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;

public class MockitoExample {


  class A{
    public String f(){
      return "f";
    }
    
    public void g(){
      String a=f();
      System.out.println(a);
    }
  }
  
  class Person {
    public void add(Integer value) {
      
    }
  }
  class MyService extends AbstractService{
    public MyService(String name) {
      super(name);
    }

    @Override
    protected void serviceInit(Configuration conf) throws Exception {
      // TODO Auto-generated method stub
      super.serviceInit(conf);
    }
    
    @Override
    protected void serviceStart() throws Exception {
      // TODO Auto-generated method stub
      super.serviceStart();
    }
  }
  
  @Test
  public void test(){
    Person p = mock(Person.class);
    ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
    p.add(new Integer(0));
    verify(p).add(captor.capture());
    for (Integer i : captor.getAllValues()) {
      System.out.println(i);
    }
    
    System.out.println("next time");
    verify(p).add(captor.capture());
    for (Integer i : captor.getAllValues()) {
      System.out.println(i);
    }
  }
}
