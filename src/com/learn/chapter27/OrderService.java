package com.learn.chapter27;

import com.learn.chapter19.Future;

public interface OrderService {

  Future<String> findOrderDetails(long orderId);

  void order(String account, long orderId);

}
