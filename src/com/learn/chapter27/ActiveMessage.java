package com.learn.chapter27;

import com.learn.chapter19.Future;
import java.lang.reflect.Method;

class ActiveMessage {

  //接口方法的参数
  private final Object[] objects;

  //接口方法
  private final Method method;

  //有返回值的方法
  private final ActiveFuture<Object> future;

  //具体的service接口
  private final Object service;

  private ActiveMessage(Builder builder) {
    this.objects = builder.objects;
    this.method = builder.method;
    this.future = builder.future;
    this.service = builder.service;
  }

  public void execute() {
    try {
      Object result = method.invoke(service, objects);
      if (future != null) {
        Future<?> realFuture = (Future<?>) result;
        Object realResult = realFuture.get();
        future.finish(realResult);
      }
    } catch (Exception e) {
      if (future != null) {
        future.finish(null);
      }
    }
  }

  static class Builder {

    private Object[] objects;
    private Method method;
    private ActiveFuture<Object> future;
    private Object service;

    public Builder useMethod(Method method) {
      this.method = method;
      return this;
    }

    public Builder retureFuture(ActiveFuture<Object> future) {
      this.future = future;
      return this;
    }

    public Builder withObjects(Object[] objects) {
      this.objects = objects;
      return this;
    }

    public Builder forService(Object service) {
      this.service = service;
      return this;
    }

    public ActiveMessage build() {
      return new ActiveMessage(this);
    }
  }

}
