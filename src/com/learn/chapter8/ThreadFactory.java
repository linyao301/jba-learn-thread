package com.learn.chapter8;

public interface ThreadFactory {

  Thread createThread(Runnable runnable);

}
