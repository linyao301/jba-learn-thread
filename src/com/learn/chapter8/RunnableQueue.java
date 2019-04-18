package com.learn.chapter8;

public interface RunnableQueue {

  void offer(Runnable runnable);

  Runnable take() throws InterruptedException;

  int size();

}
