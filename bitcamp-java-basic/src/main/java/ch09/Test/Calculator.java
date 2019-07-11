package ch09.Test;

// 계산 기능과 관련된 메서드를 별도의 블록으로 분리할 때 사용하는 문법이 "클래스"이다.
public class Calculator {
//개별적으로 관리되어야할값은 인스터스 필드에저장해야한다

  int result;

  void plus(int a) {
    // 모든 인스턴스 메소드는 호출될때 넘겨받은 인스턴스 주소를 내부에 미리 생성한(built-in) 로컬변수 this 에 보관한다
    this.result += a;
  }

  void minus(int a) {
    this.result -= a;
  }

  void multiple(int a) {
    this.result *= a;
  }

  void divide(int a) {
    this.result /= a;
  }


  // 클래스를 정의하면서 만든 기능을 간단하게 확인하고 싶을 때,
  // 다음과 같이 해당 클래스에 main() 메서드를 만들어
  // 테스트 해 볼 수 있다.
  //
  /*
   * public static void main(String[] args) { System.out.println(abs(100));
   * System.out.println(abs(-100)); }
   */
}


