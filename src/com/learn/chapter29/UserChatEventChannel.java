package com.learn.chapter29;

public class UserChatEventChannel extends AsyncChannel {

  @Override
  protected void handle(Event message) {
    UserChatEvent event = (UserChatEvent) message;
    System.out.println("用户:" + event.getUser().getName() + "发送了一条消息:" + event.getMessage());
  }
}
