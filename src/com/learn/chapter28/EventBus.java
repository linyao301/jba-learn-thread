package com.learn.chapter28;

public class EventBus implements Bus {

  //用于维护Subscriber的注册表
  private final Registry registry = new Registry();

  private String busName;

  private final static String DEFAULT_BUS_NAME = "default";

  private final static String DEFAULT_TOPIC = "default-topic";

  //用于分发广播消息到各个Subscriber的类
  private final Dispatcher dispatcher;


  @Override
  public void register(Object subscriber) {

  }

  @Override
  public void unregister(Object subscriber) {

  }

  @Override
  public void post(Object event) {

  }

  @Override
  public void post(Object event, String topic) {

  }

  @Override
  public void close() {

  }

  @Override
  public String getBusName() {
    return null;
  }
}
