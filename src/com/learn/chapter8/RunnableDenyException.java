package com.learn.chapter8;

public class RunnableDenyException extends RuntimeException {

  public RunnableDenyException() {
  }

  public RunnableDenyException(String message) {
    super(message);
  }

  public RunnableDenyException(String message, Throwable cause) {
    super(message, cause);
  }

  public RunnableDenyException(Throwable cause) {
    super(cause);
  }

  public RunnableDenyException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
