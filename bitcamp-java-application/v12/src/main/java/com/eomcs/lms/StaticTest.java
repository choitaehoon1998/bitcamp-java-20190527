package com.eomcs.lms;

public class StaticTest {
  public static void main(String[] args) {

    // 스태틱 필드 사용하기
    // 클래스 이름으로 사용하면 된다
    System.out.println(Car.Count);

    // 인스턴스 필드사용하기
    Car c1;
    c1 = new Car();
    c1.no = 1;
    c1.model = "티코";
    c1.Count++; // 스태틱필드는 보통 클래스 이름으로 사용한다
                // 그러나 클래스이름 대신 래퍼런스를 통해 스태틱 필드를 사용할수있다. 비추!
                // 개발자가 인스턴스 필드라고 착각할수있다 따라서 이런식으로 사용하지말라 !
    Car c2;
    c2 = new Car();
    c2.no = 2;
    c2.model = "소나타";
    c2.Count++;

    System.out.printf("%d %s\n", c1.no, c1.model);
    System.out.printf("%d %s\n", c2.no, c2.model);
    System.out.println(Car.Count);
  }
}
