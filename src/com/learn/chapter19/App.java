package com.learn.chapter19;

import java.util.concurrent.TimeUnit;
import sun.plugin2.jvm.CircularByteBuffer.Streamer;

public class App {

  public static void main(String[] args) throws InterruptedException {
    FutureServce futureServce = FutureServce.newServce();
    Future future = null;
    future = futureServce.submit(() -> {
      try {
        System.out.println("运行中");
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.println(future.get());
    System.out.println("运行结束");
    future = futureServce.submit(new Task<String, String>() {
      @Override
      public String get(String input) {
        try {
          System.out.println("运行中");
          TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return input;
      }
    }, "hello");
    System.out.println(future.get());
    System.out.println("运行结束");
  }

}
