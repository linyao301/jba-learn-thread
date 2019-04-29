package com.learn.chapter19;

public class FutureTask<T> implements Future<T> {

  private final Object MUTEX = new Object();

  private T result;

  private boolean isDone = false;

  @Override
  public T get() throws InterruptedException {
    synchronized (MUTEX) {
      while (!done()) {
        MUTEX.wait();
      }
      return result;
    }
  }

  protected void finish(T result) {
    synchronized (MUTEX) {
      if (isDone) {
        return;
      }
      this.result = result;
      this.isDone = true;
      MUTEX.notifyAll();
    }
  }

  @Override
  public boolean done() {
    return this.isDone;
  }
}
