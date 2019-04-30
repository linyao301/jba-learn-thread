package com.learn.chapter27;

import com.learn.chapter19.Future;
import com.learn.chapter27.ActiveMessage.Builder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActiveServiceFactory {

  private final static ActiveMessageQueue queue = new ActiveMessageQueue();

  public static <T> T active(T instance) {
    Object proxy = Proxy
        .newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(),
            new ActiveInvocationHandler<>(instance));
    return (T) proxy;
  }

  private static class ActiveInvocationHandler<T> implements InvocationHandler {

    private final T instance;

    public ActiveInvocationHandler(T instance) {
      this.instance = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.isAnnotationPresent(ActiveMethod.class)) {
        this.checkMethod(method);
        ActiveMessage.Builder builder = new Builder();
        builder.useMethod(method).withObjects(args).forService(instance);
        Object result = null;
        if (this.isReturnFutureType(method)) {
          result = new ActiveFuture<>();
          builder.retureFuture((ActiveFuture<Object>) result);
        }
        queue.offerActive(builder.build());
        return result;
      } else {
        return method.invoke(instance, args);
      }
    }

    private void checkMethod(Method method) throws IllegalActiveMethod {
      if (!(method.getReturnType().equals(Void.TYPE)) && !(method.getReturnType()
          .equals(Future.class))) {
        throw new IllegalActiveMethod("参数非法");
      }
    }

    private boolean isReturnFutureType(Method method) {
      return method.getReturnType().equals(Future.class);
    }
  }

}
