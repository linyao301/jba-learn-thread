package com.learn.chapter15;

public class EmptyTaskLifecycle<T> implements TaskLifecycle<T> {

  @Override
  public void onStart(Thread thread) {
    System.out.println(thread.getName() + " is start");
  }

  @Override
  public void onRunning(Thread thread) {
    System.out.println(thread.getName() + " is running");
  }

  @Override
  public void onFinish(Thread thread, T result) {
    System.out.println(thread.getName() + " is done,result is " + result);
  }

  @Override
  public void onError(Thread thread, Exception e) {
    System.out.println(thread.getName() + " is error,exception is" + e.getMessage());
    e.printStackTrace();
  }
}
