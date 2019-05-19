package com.learn.chapter17;

public interface Lock {

  /**
   * 加锁
   */
  void lock() throws InterruptedException;

  /**
   * 解锁
   */
  void unlock();

}
