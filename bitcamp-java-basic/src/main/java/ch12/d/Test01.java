// 캡슐화(encapsulation) : 적용전
package ch12.d;

public class Test01 {
  public static void main(String[] args) {

    Patient p = new Patient();
    p.name = "김영희";
    p.age = 20;
    p.height = 157;
    p.weight = 60;
    p.gender = Patient.WOMAN;// woman = 1; man = 2;

    System.out.println(p);
    // 환자를 컴퓨터에서 다루기위해 데이터화 해야한다 .
    // patient는 이럴 목적으로 정의한 클래스이다 .
    // 이렇게 patient 의 경우처럼 컴퓨터에서 다루기 위해 데이터화하여 정의하는것을
    // "추상화"라 부른다 .
    // 꼭 데이터만 해당하는것은 아니다 .
    // MemberHandler 클래스의 경우처럼 특정업무를 정의하는것도 추상화 라 부른다 .
    // 즉 실세계의 객체를 컴퓨터에서 다룰수있도록 클래스로 정의하는 행위를 추상화라 부른다 .

    Patient p2 = new Patient();
    p2.Name("이철희");
    p2.Age(300);
    p2.Height(-50);
    p2.Weight(400);
    p2.Gender( Patient.MAN);// woman = 1; man = 2;
    System.out.println(p2);

    // 나이가 300이면 환자가 아니라 몬스터이다 .
    // 몸무게가 -50 이면 이해 불가하다
    // 키가 4미터 이면 나무다 .
    // 즉 patient 클래스는 환자의 데이터를 저장할목적으로 정의한 클래스인데
    // 환자의 데이터와 무관한 몬스터의 데이터를 저장하고있다 .
    // 차라리 클래스이름을 monster로 변경하는것이 바람직하다.
    // 이렇게 클래스 목적에 맞지 않는 데이터가 들어갈 수있다면 .
    // "추상화"가 무너지게된다.
    // 이를 방지하기위해서는 클래스 목적(추상화 목적)에 맞춰
    // 인스턴스의 변수에 무효화 값이 들어가지 않도록 해야한다
    // 그럴목적으로 만든 문법이 캡슐화 이다 . 
    
    
    
    
    // "캡슐화" 는 추상화가 무너지지않도록 인스턴스멤버(변수, 메서드)의 접근을 제어하는 문법이다 
    // "추상화" 실세계의 객체를 프로그램에서 다를수있도록 클래스로 정의하는것
    // 추상화 기법
    // 데이터 타입을 정의
    // 유관 메서드를 묶기

  }

}