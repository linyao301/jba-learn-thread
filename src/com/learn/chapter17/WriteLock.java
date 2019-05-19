package com.learn.chapter17;

public class WriteLock implements Lock {

  private ReadWriteLockImpl readWriteLock;

  public WriteLock(ReadWriteLockImpl readWriteLock) {
    this.readWriteLock = readWriteLock;
  }

  @Override
  public void lock() throws InterruptedException {
    synchronized (readWriteLock.getMUTEX()) {
      try {
        readWriteLock.incrementWaitingWriter();
        while (readWriteLock.getReadingReaders() != 0 || readWriteLock.getWritingWriters() != 0) {
          readWriteLock.getMUTEX().wait();
        }
      } finally {
        readWriteLock.decrementWaitingWriter();
      }
      readWriteLock.incrementWritingWriter();
    }
  }

  @Override
  public void unlock() {
    synchronized (readWriteLock.getMUTEX()) {
      readWriteLock.decrementWritingWriter();
      readWriteLock.setPerferWriter(false);
      readWriteLock.getMUTEX().notifyAll();
    }
  }
}
