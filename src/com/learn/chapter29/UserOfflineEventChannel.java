package com.learn.chapter29;

public class UserOfflineEventChannel extends AsyncChannel {

  @Override
  protected void handle(Event message) {
    UserOfflineEvent event = (UserOfflineEvent) message;
    System.out.println("用户" + event.getUser().getName() + "下线了");
  }
}
