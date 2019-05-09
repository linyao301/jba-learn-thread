package com.learn.chapter28;

public interface Bus {

  /**
   * 将某个对象注册到bus上
   */
  void register(Object subscriber);

  /**
   * 将某个对象从bus上取消注册
   */
  void unregister(Object subscriber);

  /**
   * 提交Event到默认的topic
   */
  void post(Object event);

  /**
   * 提交Event到执行的topic
   */
  void post(Object event, String topic);

  /**
   * 关闭该bus
   */
  void close();

  /**
   * 返回Bus的名称标识
   */
  String getBusName();
}
