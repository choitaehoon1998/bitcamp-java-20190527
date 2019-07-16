// Wrapper 클래스 : 사용전
package ch11;

import java.util.Date;

public class Test09_3 {
  public static void main(String[] args) {
    // primitive type의 값을 전달할때는 test09_2 의경우와같이
    // 각타입의 값을 받는 메서드를 여러개 만들어야하지만
    // wrapper 클래스의 인스턴스를 사용하면 타입의 종류와 상관없이
    // 파라미터로 값을 받는 메소드를 한개만 만들어도된다. .
    Integer obj1 = Integer.valueOf(100);
    Float obj2 = Float.valueOf(3.14f);
    Character obj3 = Character.valueOf('A');
    Boolean obj4 = Boolean.valueOf(true);
    
    printObject(obj1);
    printObject(obj2);
    printObject(obj3); // 컴파일 오류!
    printObject(obj4);


  }

  static void printObject(Object obj) {
    System.out.println("값 => " + obj.toString());
  }

}
