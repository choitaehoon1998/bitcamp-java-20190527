//잘못된 값을 넣었을때 무시하지않고 강력하게 오류를 알리기 !
package ch12.d;

public class Test3 {
public static void main(String[] args) {
  Patient p = new Patient();
  p.setName("이영희");
  p.setAge(20);
  p.setHeight(157);
  p.setWeight(60);
  p.setGender( Patient.WOMAN);// woman = 1; man = 2;

  System.out.println(p);
  
  Patient3 p2 = new Patient3();
  p2.setName("이철희");
  p2.setAge(300);// 캡슐화 를 무너뜨릴수있는 유효하지않은값은 무시된다 . 
  p2.setHeight(-50);// 캡슐화 를 무너뜨릴수있는 유효하지않은값은 무시된다 . 
  p2.setWeight(400);
  p2.setGender(3);// woman = 1; man = 2;// 캡슐화 를 무너뜨릴수있는 유효하지않은값은 무시된다 . 
  System.out.println(p2);
}
}
