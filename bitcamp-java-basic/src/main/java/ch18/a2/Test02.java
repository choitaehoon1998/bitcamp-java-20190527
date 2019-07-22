// 인터페이스 규칙에 따라 만든 클래스를 사용하기 .
package ch18.a2;

public class Test02 {

  public static void main(String[] args) {
    // 도구 사용하기
    // => Spec규칙(iNTERFACE)에 따라 만든 도구를 use()메서드에 넘긴다.
    use(new ToolA());
    use(new ToolB());
  }

  static void use(Spec tool) {
    // 파라미터 tool 에 넘어온 객체는 SPEC 규칙에 따라 만든 객체일것이다.
    // sPEC 규칙에 따라 만든 도구를 사용할때는
    // SPEC 규칙에 따라 일관된 방식으로 사용 *(메서드 호출) 하면된다 .
    // 그러면 해당 인스턴스의 클래스를 찾아 그클래스에서 구현한 메서드를 호출할것이다.
    tool.m1();
  }

}


