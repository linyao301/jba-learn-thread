package com.learn.chapter27;

import com.learn.chapter19.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {

  @Override
  public void finish(T result) {
    super.finish(result);
  }
}
