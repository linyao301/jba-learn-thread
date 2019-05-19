package com.learn.chapter17;

public interface ReadWriteLock {

  /**
   * 读锁
   */
  Lock readLock();

  /**
   * 写锁
   */
  Lock writeLock();

  /**
   * 获取正在读取的线程数
   */
  int getReadingReaders();

  /**
   * 获取正在写入的线程数
   */
  int getWritingWriters();

  /**
   * 获取等待写锁的线程数量
   */
  int getWaitingWriters();

  static ReadWriteLock readWriteLock() {
    return new ReadWriteLockImpl();
  }

  static ReadWriteLock readWriteLock(boolean perferWriter) {
    return new ReadWriteLockImpl(perferWriter);
  }

}
