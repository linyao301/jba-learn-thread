package com.learn.chapter28;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

class Registry {

  private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscriberContainer = new ConcurrentHashMap<>();

  public void bind(Object subscriber) {
    List<Method> subscriberMethods = getSubscribeMethods(subscriber);
    subscriberMethods.forEach(m -> tierSubscriber(subscriber, m));
  }

  public void unbind(Object subscriber) {
    subscriberContainer.forEach((key, queue) -> queue.forEach(s -> {
      if (s.getSubscriber() == subscriber) {
        s.setDisable(true);
      }
    }));
  }

  public ConcurrentLinkedQueue<Subscriber> scanSubscriber(final String topic) {
    return subscriberContainer.get(topic);
  }

  private void tierSubscriber(Object subscriber, Method method) {
    final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
    String topic = subscribe.topic();
    subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
    subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
  }

  private List<Method> getSubscribeMethods(Object subscriber) {
    final List<Method> methods = new ArrayList<>();
    Class<?> temp = subscriber.getClass();
    while (temp != null) {
      Method[] declaredMethods = temp.getDeclaredMethods();
      Arrays.stream(declaredMethods).filter(
          m -> m.isAnnotationPresent(Subscribe.class) && m.getParameterCount() == 1
              && m.getModifiers() == Modifier.PUBLIC).forEach(methods::add);
      temp = temp.getSuperclass();
    }
    return methods;
  }
}
