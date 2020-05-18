package com.learn.other;

public class TwinsLockTest {

  private static final TwinsLock twinsLock = new TwinsLock();

  private static class WorkerThread extends Thread {

    public WorkerThread(String name) {
      super(name);
    }

    @Override
    public void run() {
      twinsLock.lock();
      try {
        Thread.sleep(2 * 1000);
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2 * 1000);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        twinsLock.unlock();
      }
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      WorkerThread threadi = new WorkerThread("worker:" + i);
      threadi.start();
    }
  }

}
