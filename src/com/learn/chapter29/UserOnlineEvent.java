package com.learn.chapter29;

public class UserOnlineEvent extends Event {

  private final User user;

  public UserOnlineEvent(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
