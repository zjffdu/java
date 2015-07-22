package com.zjffdu.tutorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tez.dag.api.TezConfiguration;
import org.apache.tez.dag.app.TestMockDAGAppMaster;
import org.apache.tez.dag.app.dag.impl.TestCommit;
import org.apache.tez.dag.app.dag.impl.TestEdge;
import org.apache.tez.dag.app.dag.impl.TestVertexImpl;
import org.apache.tez.test.TestAMRecovery;
import org.apache.tez.test.TestFaultTolerance;
import org.junit.internal.requests.ClassRequest;
import org.junit.internal.requests.FilterRequest;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.notification.Failure;

public class Test {

  private static final Log LOG = LogFactory.getLog(Test.class);

  private static String exec(String command) throws IOException {
    LOG.info("exec command:" + command);
    Process process = Runtime.getRuntime().exec(command);
    BufferedReader errorReader = new BufferedReader(new InputStreamReader(
        process.getErrorStream()));
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
        process.getInputStream()));
    StringBuilder response = new StringBuilder();
    String line = null;
    while ((line = inputReader.readLine()) != null) {
      LOG.info("Command stdin:" + line);
      response.append(line + "\n");
    }
    while ((line = errorReader.readLine()) != null) {
      LOG.error("Command stderr:" + line);
      response.append(line + "\n");
    }
    return response.toString();
  }

  private class A {
    public String f(int a) {
      return null;
    }
  }

  public static volatile boolean stopFlag = false;

  public static void main(String[] args) throws Exception {
    FilterRequest request = new FilterRequest(new ClassRequest(
        TestVertexImpl.class), new Filter() {

      @Override
      public boolean shouldRun(Description desc) {

//        return true;
        return desc.getMethodName().equals(
            "testInputInitializerEventsAtNew");
      }

      @Override
      public String describe() {

        return null;
      }
    });

    Result result = null;
    do {
      JUnitCore core = new JUnitCore();
      result = core.run(request);
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
    } while (result.wasSuccessful());

    List<Failure> failures = result.getFailures();
    for (Failure fail : failures) {
      System.out.println(fail.getDescription());
      System.out.println(fail.getTrace());
    }

  }
}
