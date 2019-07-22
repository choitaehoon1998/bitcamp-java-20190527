package ch18.f;

// 자동차의 기능을 갖추려면 CarSpec 을 만족시켜야하는데 ,
// 직접 CarSpec인터페이스를 구현하는 대신에
// carSpec을 미리 구현한 AbSTRACTCAR를 상속받는것이
// 클래스를 만들기 편하다.
public class Truck extends AbstractCar {


  // CarSpec 에 선언된 on() off() 메서드는 수퍼클래스에서 미리구현했기때문에
  // 서브클래스에서 다시 구현할 필요가없어 편하다

  // 서브 클래스는 수퍼클래스가 구현하지않은 나머지 메서드만 구현하면 된다 .
  @Override
  public void run() {
    System.out.println("덜컹 덜컹 달린다!");
  }
}
