package com.learn.chapter29;

public class UserOnlineEventChannel extends AsyncChannel {

  @Override
  protected void handle(Event message) {
    UserOnlineEvent event = (UserOnlineEvent) message;
    System.out.println("用户" + event.getUser().getName() + "上线了");
  }
}
