/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zjffdu.tutorial;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DeadLock {

  private Lock readLock;
  private Lock writeLock;
  private Condition condition;

  public DeadLock() {
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    this.readLock = readWriteLock.readLock();
    this.writeLock = readWriteLock.writeLock();
    this.condition = writeLock.newCondition();
  }

  public void go() {
    new Thread1().start();
    new Thread2().start();
  }
  public class Thread1 extends Thread {

    @Override
    public void run() {
      writeLock.lock();
      try {
        System.out.println("wait...");
        try {
          condition.awaitNanos(0);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } finally {
        writeLock.unlock();
      }
    }
  }

  public  class Thread2 extends Thread {


    @Override
    public void run() {
      readLock.lock();
      try {
        System.out.println("notify...");
        condition.signal();
      } finally {
        readLock.unlock();
      }
    }
  }

  public static void main(String[] args) {
//    new DeadLock().go();
    Set<String> set = new HashSet<String>();
    set.add("fdaf");
    System.out.println(set);
  }
}
