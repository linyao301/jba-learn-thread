package com.learn.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {

  private Thread currentThread;

  private boolean lock;

  private List<Thread> blockedList = new ArrayList<>();

  private Thread currentThread() {
    return Thread.currentThread();
  }

  @Override
  public void lock() throws InterruptedException {
    synchronized (this) {
      while (lock) {
        blockedList.add(currentThread());
        this.wait();
      }
      blockedList.remove(currentThread());
      lock = true;
      currentThread = currentThread();
    }
  }

  @Override
  public void lock(long timeout) throws InterruptedException, TimeoutException {

  }

  @Override
  public void unlock() {

  }

  @Override
  public List<Thread> getBlockedThreads() {
    return null;
  }
}
