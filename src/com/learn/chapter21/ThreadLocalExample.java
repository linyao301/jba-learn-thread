package com.learn.chapter21;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ThreadLocalExample {

  public static void main(String[] args) {
    ThreadLocal<Integer> tlocal = ThreadLocal.withInitial(new Supplier<Integer>() {
      @Override
      public Integer get() {
        return 1;
      }
    });
    IntStream.range(0, 10).forEach(x -> new Thread(() -> {
      tlocal.set(x);
      System.out.println(Thread.currentThread() + " set x " + tlocal.get());
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread() + " get x " + tlocal.get());
    }).start());
  }

}
