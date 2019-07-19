package com.eomcs.util;
//목록을 다루는 객체가 반드시 갖춰야할 기능을 규칙으로 정의한다 .
//사용하는 측에서는 이규칙에 따라 일관된 방식으로 클래스를 사용할 수 있다 . 
public interface List<E> {
  
  
  // 기능을 정의할때는 메서드 시그너처만 선언한다 . 
  // 기능의 구현은 클래스에서 할것이다 . 
  // => 규칙은  무조건 공개되어야 한다 .따라서 public 이다 
  // 규칙에 정의된 메서드는 클래스에서 구현해야한다 . 그래서 abstract 이다 
  // public abstract modifier 은 생략할수있다 . 
  
  public boolean add(E value);
  abstract E get(int index);
  public abstract E set(int index , E value);
  E remove(int index);
  Object[] toArray();
  E[] toArray(E[] a);
  int size();
  void clear();
}
