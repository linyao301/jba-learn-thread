package com.learn.chapter5;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {

  void lock() throws InterruptedException;

  void lock(long timeout) throws InterruptedException, TimeoutException;

  void unlock();

  List<Thread> getBlockedThreads();

}
