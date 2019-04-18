package com.learn.chapter15;

@FunctionalInterface
public interface Task<T> {

  T call();

}
