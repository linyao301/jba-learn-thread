package com.learn.chapter15;

public interface TaskLifecycle<T> {

  void onStart(Thread thread);

  void onRunning(Thread thread);

  void onFinish(Thread thread, T result);

  void onError(Thread thread, Exception e);

}
