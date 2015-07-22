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

package com.zjffdu;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;

public class Example {

  public static void main(String[] args) throws IOException {
    Reflections reflections =
        new Reflections(ClasspathHelper.forPackage("org.apache.tez"),
            new SubTypesScanner(), new TypeAnnotationsScanner(),
            new MethodAnnotationsScanner());

    Set<Method> methods = reflections.getMethodsAnnotatedWith(Test.class);
    for (Method method : methods) {
      Test t = method.getAnnotation(Test.class);
      if (t.timeout() == 0) {
      System.out.println(method+"," + method.getName() + ":" + t.timeout());
      }
    }
  }
}
