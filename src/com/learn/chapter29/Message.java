package com.learn.chapter29;

public interface Message {

  /**
   * 返回Message的类型
   */
  Class<? extends Message> getType();

}
