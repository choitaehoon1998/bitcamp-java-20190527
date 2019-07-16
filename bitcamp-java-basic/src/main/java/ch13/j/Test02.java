// 추상 클래스: 파라미터로 사용하기 .
package ch13.j;

public class Test02 {

  public static void main(String[] args) {
    
   m1 (new DumpTruck());
   
   //m2(new Car()); ==불가능
   m2 (new DumpTruck());
   m2 (new Convertible());
   
  }

  static void m1(DumpTruck car) {
   //파리미터가 Dump Truck이면 ,
    // 이메소드를 호출할때 반드시 DumpTruck 의 인스턴스나 
    // 또는 DumpTruck의 하위클래스의 인스턴스를 넘기라는 의미이다 . 
    
  }
  
  static void m2(Car car) {
    //파리미터가 car 이다 
    // 이메서드를 호출할때 car의 하위클래스의 인스턴스를 넘기라는 뜻이다
    // car는 추상클래스 이기때문에 인스턴스를 생성할수없다 . 
  }
  
}
