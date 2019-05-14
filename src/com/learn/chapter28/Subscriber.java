package com.learn.chapter28;

import java.lang.reflect.Method;

public class Subscriber {

  private final Object subscriber;

  private final Method method;

  private boolean disable = false;

  public Subscriber(Object subscriber, Method method) {
    this.subscriber = subscriber;
    this.method = method;
  }

  public Object getSubscriber() {
    return subscriber;
  }

  public Method getMethod() {
    return method;
  }

  public boolean isDisable() {
    return disable;
  }

  public void setDisable(boolean disable) {
    this.disable = disable;
  }
}
