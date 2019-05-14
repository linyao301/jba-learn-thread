package com.learn.chapter28;

import java.util.concurrent.Executor;

public class EventBus implements Bus {

  //用于维护Subscriber的注册表
  private final Registry registry = new Registry();

  private String busName;

  private final static String DEFAULT_BUS_NAME = "default";

  private final static String DEFAULT_TOPIC = "default-topic";

  //用于分发广播消息到各个Subscriber的类
  private final Dispatcher dispatcher;

  public EventBus() {
    this(DEFAULT_BUS_NAME, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
  }

  public EventBus(String busName) {
    this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
  }

  public EventBus(String busName, EventExceptionHandler exceptionHandler) {
    this(busName, exceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
  }

  public EventBus(String busName, EventExceptionHandler exceptionHandler, Executor executor) {
    this.busName = busName;
    this.dispatcher = Dispatcher.newDispatcher(exceptionHandler, executor);
  }

  public EventBus(EventExceptionHandler exceptionHandler) {
    this(DEFAULT_BUS_NAME, exceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
  }

  @Override
  public void register(Object subscriber) {
    this.registry.bind(subscriber);
  }

  @Override
  public void unregister(Object subscriber) {
    this.registry.unbind(subscriber);
  }

  @Override
  public void post(Object event) {
    this.post(event, DEFAULT_TOPIC);
  }

  @Override
  public void post(Object event, String topic) {
    this.dispatcher.dispatch(this, registry, event, topic);
  }

  @Override
  public void close() {
    this.dispatcher.close();
  }

  @Override
  public String getBusName() {
    return this.busName;
  }
}
