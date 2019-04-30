package com.learn.chapter27;

import com.learn.chapter19.Future;

public interface OrderService {

  @ActiveMethod
  Future<String> findOrderDetails(long orderId);

  void order(String account, long orderId);

}
