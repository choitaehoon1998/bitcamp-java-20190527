package com.eomcs.util;
// Iterator 를 제공하는 클래스를 갖춰야할 메서드 규칙을 정의한다.
// Iterator 가 필요한 개발자는 다음규칙에 따라 메서드를 호출하여
// Iterator 를 얻는다
// 즉 Iterator를 얻는 방법도 일관성 있게 하기 위함이다
// 이방법을 도입하기전에는
// stack 클래스에서는 getItetator()메서드를 제공하였고
// quque 클래스에서는 createItetator()메서드를 제공하였다
// 이렇게 Itetator 를 리턴해주는 메서드 이름이 다르면
// 소스코드를 유지보수하기 불편하다 . 
public interface Iterable <E>{
  // 다음 메서드를 호출하면 Iterator 구현체를 리턴할것이다
  // stack 과 queue 는 이규칙을 준수해야한다
  // 그래야 개발자가 일관된 방식으로 iTEiterator를 얻을수있따
  Iterator<E> iterator();

}
