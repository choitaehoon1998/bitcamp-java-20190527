// hash code 응용 -문제해결
package ch15;

import java.util.HashSet;

public class Test08_2 {
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
    HashSet<Student> set = new HashSet<>();
    set.add(s1);
    set.add(s2);
    set.add(s3);
    set.add(s4);

    Object [] List = set.toArray();
    for (Object obj : List) {
      Student student = (Student) obj;
      System.out.printf("%s, %d, %s\n", student.age, student.name, student.working ? "재직중" : "실업중");
// 인스턴스가 다르더라도 인스턴스의 필드값이 같을경우
// hashset 에 중복 저장 되지않도록 하려면
// hashcode 와 equals 몯 오버라이딩 하라
      // hashcode 는 같은필드값을 갖는경우 같은 해시코드를 리턴하도록 변경하고
      // equals 는 필드값이 같을경우  true를 리턴하도록 변경하라 . 
    }
  }
}
