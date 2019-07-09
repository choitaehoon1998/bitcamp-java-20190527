package com.eomcs.lms;

public class Car {
  // static/class field
  // 클래스가 로딩될때 메소드 에어리어에 자동생성된다
  // 클래스는 딱한번만 로딩되기때문에 (중복으로 로딩되지않는다)
  // 스태틱 필드도 딱한번만 생성된다
  static int Count;
  // instance field
  // new 명령을 실행할떄 heap에 생성된다
  // new 명령을 실행하는 갯수 만큼 인스턴스 필드가 생성된다 . 
  int no;
  String model;

}
