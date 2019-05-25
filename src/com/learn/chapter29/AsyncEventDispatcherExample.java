package com.learn.chapter29;

import com.learn.chapter29.EventDispatcherExample.InputEvent;
import com.learn.chapter29.EventDispatcherExample.ResultEvent;
import java.util.concurrent.TimeUnit;

public class AsyncEventDispatcherExample {

  static class AsyncInputEventHandler extends AsyncChannel {

    private final AsyncEventDispatcher dispatcher;

    public AsyncInputEventHandler(AsyncEventDispatcher dispatcher) {
      this.dispatcher = dispatcher;
    }

    @Override
    protected void handle(Event message) {
      InputEvent inputEvent = (InputEvent) message;
      System.out.printf("X:%d,Y:%d\n", inputEvent.getX(), inputEvent.getY());
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      int result = inputEvent.getX() + inputEvent.getY();
      dispatcher.dispatch(new ResultEvent(result));
    }
  }

  static class AsyncResultEventHandler extends AsyncChannel {

    @Override
    protected void handle(Event message) {
      ResultEvent resultEvent = (ResultEvent) message;
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("result is:" + resultEvent.getResult());
    }
  }

  public static void main(String[] args) {
    AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();
    dispatcher.registerChannel(InputEvent.class, new AsyncInputEventHandler(dispatcher));
    dispatcher.registerChannel(ResultEvent.class, new AsyncResultEventHandler());
    dispatcher.dispatch(new InputEvent(1, 2));
    dispatcher.dispatch(new InputEvent(3, 4));
    dispatcher.dispatch(new InputEvent(5, 6));
  }

}
