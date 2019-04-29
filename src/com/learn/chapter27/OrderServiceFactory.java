package com.learn.chapter27;

public class OrderServiceFactory {

  private final static ActiveMessageQueue queue = new ActiveMessageQueue();

  private OrderServiceFactory() {

  }

  public static OrderService toActivrObject(OrderService orderService) {
    return new OrderServiceProxy(orderService, queue);
  }

}
