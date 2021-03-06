// 스태틱 멤버와 인스턴스 멤버
package ch10;

public class Test02_1 {
  // 스태틱 멤버 -
  // 1.스태틱 필드 .
  // => 클래스가 로딩될떄 생성된다 .
  static int f1;
  static {
    // 2.스태틱 초기화 블럭
    // => 스태틱 필드가 모두 생성된후 실행된다 .
  }
  static void m1() {
    // 3.스태틱 메서드
    // => 클래스 이름으로 호출한다 .
  }
  // 인스턴스 멤버 -
  // 1.인스턴스 필드
  // => new 명령을 실행할때 heap 에 생성된다
  int f2;
  {
    // 2. 인스턴스 초기화 블럭
    // => 인스턴스 필드가 생성된후 실행된다 .
  }
  Test02_1() {
    // 3. 생성자
    // => 인스턴스 초기화 블럭이 실행된후 생성자가 호출된다 .
    // => 물론 new 명령에서 어떤 생성자를 호출해야할지 지정해야한다
  }
  void m2() {
    // 4. 인스턴스 메서드
    // => 인스턴스 가 있어야만 호출할수있다
    // => 메서드를 호출할떄 넘겨준 인스턴스 주소는
    // this 라는 내장변수(built-in)에 보관된다
  }
}


