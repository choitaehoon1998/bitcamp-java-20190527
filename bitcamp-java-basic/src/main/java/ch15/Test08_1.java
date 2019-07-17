// hash code 응용 -HashSet과 Hashcode()
package ch15;

import java.util.HashSet;

public class Test08_1 {
 static class Student {
    String name;
    int age;
    boolean working;

    public Student(String name, int age, boolean working) {
      this.name = name;
      this.age = age;
      this.working = working;
    }
  }

  public static void main(String[] args) {
    Student s1 = new Student("홍길동", 20, false);
    Student s2 = new Student("홍길동", 20, false);
    Student s3 = new Student("임꺽정", 21, true);
    Student s4 = new Student("유관순", 22, true);
    System.out.println(s1 == s2);
    System.out.println(s1.hashCode());
    System.out.println(s2.hashCode());
    System.out.println(s3.hashCode());
    System.out.println(s4.hashCode());
    System.out.println("---------------------------------------");
    HashSet<Student> set = new HashSet<Student>();
    set.add(s1);
    set.add(s2);
    set.add(s3);
    set.add(s4);

    Object []List = set.toArray();
    for (Object obj : List) {
      Student student = (Student) obj;
      System.out.printf("%s, %d, %s\n", student.age, student.name, student.working ? "재직중" : "실업중");
      // 집합 => 중복값을 저장할수없다
      // Hashset => 집합의 기능을 수행한다 중복값을 저장하지않는다/
      // =>값을 저장할떄 객체의 해쉬 코드로 중복여부를 검사한다
      // =>값을 저장할때 객체의 해쉬값으로 저장할위치를 저장한
      // =>해시값이 다르면 다른값으로 취급한다
      // =>또한 해쉬코드로 값을 저장할 인덱스를 결정하기때문에 따라서 값을 꺼낼때 저장한 순서대로 꺼낼수없다

      // 현재 예제의 문제점
      // =>s1 s2 는 같은데이터를 갖고잇지만 다른 해시코드를리턴하기때문에
      // s1 과 s2를 다른값으로 취급한다
      // 그래서 s1 s2 모두 Hashset 에 저장된것이다.
      // 해결책 :
      // => 스튜던트 클래스에서 hashcode를 오버 라이딩 하여
      // 같은 데이터에대해 같은 해시코드를 리턴하도록 만든다 .
    }
  }
}


