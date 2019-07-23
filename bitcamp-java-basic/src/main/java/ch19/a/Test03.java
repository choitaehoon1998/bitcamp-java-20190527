// 중첩 클래스 사용 .static nested class와 inner 클래스 사용 . 
package ch19.a;
class X{
  static class A{}
  class B{}
  static void m1() {
    // 같은 스태틱 멤버이기때문에static nested class 를 바로사용할수있따
    A obj = new A();
    
    // 스태틱 멤버는 인스턴스 멤버를 바로 사용할수없다 
     B obj2;//레퍼런스를 선언할때는 괜찮다
     //obj2 = new B();// 컴파일 오류 . 
     //컴파일 오류가 발생한 이유 
     // 다음 과 같이 스태틱 메서드에서 인스턴스 메서드를 호출 하지 못하는 이유와 같다
     // 스태틱 메서드에서는 인스턴스 주소를 담은 this라는 변수가 없기때문이다 . 
     // 인스턴스 멤버를 사용하려면 반드시 인스턴스 주소가있어야한다 . 
    //m2(); // 컴파일 오류 . 
 
     // 만약 인스턴스 멤버를 사용해야 한다면 다음과같이 별도로 인스턴스를 생성한후 사용해야한다 
     X x = new X();
     x.m2();  //ok!
     
     //당연히 인스턴스 주소만 있다면 x의 INNER 클래스인 B객체로 생성할수있다 
     obj2 = x.new B();// 실무에서는 외부의 인스턴스를 가지로 inner 클래스를 생성하는 방식으로 거의 사용하지않는다
                      // 참고만 하라 
     
  }
  void m2() {
    //인스턴스 멤버에서 static nested clas 와 inner 클래스 사용하기 
    
    // 스태틱 멤버는 인스턴스 멤버에서도 자유롭게 사용할수잇다 . 
    // 즉 다음과같이 인스턴스 메서드에서 스태틱 메서드를 자유롭게 호출하듯이 ,
    m1();//ok
    
    //인스턴스 메서드에서 static nested class 를 바로 사용할수있따 . 
    A obj = new A();
    
    // 인스턴스 메서드에서 다른 인스턴스 멤버를 자유롭게 사용하듯이 ,
    this.a=100;
    a=100;//this 생략가능
    this.m3();
    m3();//this 생략가능
    B obj2 = this.new B();
    obj2 = new B();//this 생략가능
  }
  int a; 
  void m3() {}
}

public class Test03 {
  public static void main(String[] args) {
  // 다른 클래스에서 중첩클래스를 사용하기 
  // static nested class 사용 
    X.A obj = new X.A();
  //non static nested class 사용 
    X.B obj2 ;
    
    // X 의 inner 클래스를 사용하려면 반드시 X 의 인스턴스가 있어야한다 
    //obk2 = new X.B();//컴파일오류
    X x = new X();
    obj2 = x.new B();//ok !  
  }
  
  
}


