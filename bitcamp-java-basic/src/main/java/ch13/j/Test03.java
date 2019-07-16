// 추상 클래스: 리턴타입으로 사용하기
package ch13.j;

public class Test03 {

  public static void main(String[] args) {
    DumpTruck car = m1();
    Car car2 = m2();
  }

  static DumpTruck m1() {
    // 파리미터가 Dump Truck이면 ,
    // 이메소드를 호출할때 반드시 DumpTruck 의 인스턴스나
    // 또는 DumpTruck의 하위클래스의 인스턴스를 넘기라는 의미이다 .

    return new DumpTruck();
  }

  static Car m2() {
    // 파리미터가 car 이다
    // 이메서드를 호출할때 car의 하위클래스의 인스턴스를 넘기라는 뜻이다
    // car는 추상클래스 이기때문에 인스턴스를 생성할수없다 .

    // 이 메서드의 리턴값은 car의 인스턴스가 아니다
    // 왜 카는 추상클래스이기때문에 인스턴스를 생성할수없다
    // 그럼? 카의 하위클래수의 인스턴스를 리턴한다는것이다
    return new DumpTruck();
  }

}
