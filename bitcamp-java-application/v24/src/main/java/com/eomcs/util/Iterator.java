package com.eomcs.util;

// 컬렉션에서 값을 꺼내는 기능을 객체화 시킨다 .
// =>컬렉션에서 값을 꺼내는 기능을 클래스로 정의한다 .
// =>컬렉션에서 값을 꺼내는 기능을 추상화한다 .
// =>컬렉션에서 값을 꺼내는 기능을 캡슐화한다 .

// 컬렉션에서 값을 꺼내는 객체를 이터레이터 (Iterator:반복자)라 부른다 
// =>Iterator을 일관된 방법으로 사용할수있도록 다음과같이 규칙을 정의한다. 
// =>Iteartor의 메서드를 일관된 방법으로 호출할수있도록 다음과같이 메서드 규칙을 정의한다 . 
public interface Iterator<E> {
// 컬렉션에서 꺼낼 데이터가 있는지 검사할때 호출할 메서드
  boolean hasNext();
  //컬렉션에서 한개의 데이터를 꺼낼때 호출할 메서드 
  E Next();
}