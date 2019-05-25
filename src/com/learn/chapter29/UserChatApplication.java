package com.learn.chapter29;

public class UserChatApplication {

  public static void main(String[] args) {
    AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();
    dispatcher.registerChannel(UserOnlineEvent.class, new UserOnlineEventChannel());
    dispatcher.registerChannel(UserOfflineEvent.class, new UserOfflineEventChannel());
    dispatcher.registerChannel(UserChatEvent.class, new UserChatEventChannel());
    new UserCharThread(new User("李"), dispatcher).start();
    new UserCharThread(new User("梁"), dispatcher).start();
    new UserCharThread(new User("孔"), dispatcher).start();
  }

}
