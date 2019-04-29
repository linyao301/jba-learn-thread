package com.learn.chapter19;

public interface Task<IN, OUT> {

  OUT get(IN input);
}
