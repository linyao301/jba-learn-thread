package com.learn.chapter29;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncEventDispatcher implements DynamicRouter<Event> {

  private final Map<Class<? extends Event>, AsyncChannel> routerTable;

  public AsyncEventDispatcher() {
    this.routerTable = new ConcurrentHashMap<>();
  }

  @Override
  public void registerChannel(Class<? extends Event> messageType,
      Channel<? extends Event> channel) {
    if (!(channel instanceof AsyncChannel)) {
      throw new RuntimeException("channel 必须是AsyncChannle");
    }
    this.routerTable.put(messageType, (AsyncChannel) channel);
  }

  @Override
  public void dispatch(Event message) {
    if (routerTable.containsKey(message.getType())) {
      routerTable.get(message.getType()).dispatch(message);
    } else {
      throw new RuntimeException("未匹配到通道," + message.getType());
    }
  }

  public void shutdown() {
    routerTable.values().forEach(AsyncChannel::stop);
  }
}
