package com.learn.chapter17;

public class ReadLock implements Lock {

  private ReadWriteLockImpl readWriteLock;

  public ReadLock(ReadWriteLockImpl readWriteLock) {
    this.readWriteLock = readWriteLock;
  }

  @Override
  public void lock() throws InterruptedException {
    synchronized (readWriteLock.getMUTEX()) {
      while (readWriteLock.getWritingWriters() > 0 || (readWriteLock.getWaitingWriters() != 0
          && readWriteLock.isPerferWriter())) {
        readWriteLock.getMUTEX().wait();
      }
      readWriteLock.incrementReadingReader();
    }
  }

  @Override
  public void unlock() {
    synchronized (readWriteLock.getMUTEX()) {
      readWriteLock.decrementReadingReader();
      readWriteLock.setPerferWriter(true);
      readWriteLock.getMUTEX().notifyAll();
    }
  }
}
