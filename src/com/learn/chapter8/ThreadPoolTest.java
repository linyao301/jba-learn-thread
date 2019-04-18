package com.learn.chapter8;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

  public static void main(String[] args) throws InterruptedException {
    ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 10);
    for (int i = 0; i < 10; i++) {
      threadPool.execute(new Runnable() {
        @Override
        public void run() {
          try {
            TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }
    while (true) {
      TimeUnit.SECONDS.sleep(30);
      threadPool.shutdown();
      System.out.println("OK");
      Thread.currentThread().join();
    }
  }

}
