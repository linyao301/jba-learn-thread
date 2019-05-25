package com.learn.chapter29;

import java.util.concurrent.TimeUnit;

public class UserCharThread extends Thread {

  private final User user;
  private final AsyncEventDispatcher dispatcher;

  public UserCharThread(User user, AsyncEventDispatcher dispatcher) {
    super(user.getName());
    this.user = user;
    this.dispatcher = dispatcher;
  }

  @Override
  public void run() {
    dispatcher.dispatch(new UserOnlineEvent(user));
    for (int i = 0; i < 5; i++) {
      dispatcher.dispatch(new UserChatEvent(user, "-Hello-" + i));
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    dispatcher.dispatch(new UserOfflineEvent(user));
  }
}
