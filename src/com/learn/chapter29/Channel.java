package com.learn.chapter29;

public interface Channel<E extends Message> {

  /**
   * Message消息的分发处理
   */
  void dispatch(E message);

}
