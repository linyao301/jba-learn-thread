package com.learn.chapter29;

public interface DynamicRouter<E extends Message> {

  /**
   * 注册channel通道
   */
  void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

  /**
   * 为响应的Channel分配Message
   */
  void dispatch(E message);

}
