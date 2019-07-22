// 인터페이스 래퍼런스와 인스턴스의 관계 
package ch18.a2;

public class Test01 {

  public static void main(String[] args) {
    // 인터페이스 레퍼런스 선언 
    // => SPEc 인터페이스를 구현한 클래스의 인스턴스 주소를 저장하겠다는 의미이다. 
    // => SPEc 규칙에 따라 작성한 클래스의 객체를 담겠다는 의미이다 . 
    // => SPEc 객체를 담겠다는 의미이다.
    
    
    Spec tool;
    // toolA 클래스는 spec인터페이스의 규칙에따라 만든 클래스이기때문에
    // 이클래스의 인스턴스 주소를 tool 래퍼런스 변수에 저장할수있다 
    // 
    tool = new ToolA();
    // toolB 클래스도 ToolA 와 마찬가지로 Spec(implementer : 인터페이스 규칙에 따라 만든 클래스 ) 구현체 이기때문에 
    // 해당객체를 저장할수있다
    tool = new ToolB();
    
    // String 클래스는 spec구현체가 아니기때문에 해당객체를 래퍼런스에 저장할수없다 . 
    //tool = new String("hello");// 컴파일 오류 
  }
}







