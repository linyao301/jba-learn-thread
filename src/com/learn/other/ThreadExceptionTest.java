package com.learn.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExceptionTest {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
        executor.execute(new Runnable() {
          @Override
          public void run() {
            System.out.println("=========11==========");
          }
        });
        try {
          TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        executor.execute(new Run1());
        executor.execute(new Runnable() {
          @Override
          public void run() {
            System.out.println("END");
          }
        });
      }
    });
    thread.setDaemon(true);
    thread.start();
    TimeUnit.SECONDS.sleep(4);
    System.out.println("OK");
  }

  private static class Run1 implements Runnable {

    @Override
    public void run() {
      int count = 0;
      while (true) {
        count++;
        System.out.println(Thread.currentThread().getName() + "=====22=========" + count);
        if (count == 1) {
          System.out.println(1 / 0);
        }
        if (count == 20) {
          break;
        }
      }
    }
  }

}
