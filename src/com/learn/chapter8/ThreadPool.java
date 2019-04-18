package com.learn.chapter8;

public interface ThreadPool {

  void execute(Runnable runnable);

  void shutdown();

  int getInitSize();

  int getMaxSize();

  int getCoreSize();

  int getQueueSize();

  int getActiveCount();

  boolean isShutDown();

}
