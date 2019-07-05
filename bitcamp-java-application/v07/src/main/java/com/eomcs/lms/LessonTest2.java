package com.eomcs.lms;

public class LessonTest2 {
  public static void main(String[]args) {
    //레퍼런스 배열을 가르키는 래퍼런스 만들기 
        Lesson[] arr; //arr [배열 주소]
    //Lesson 래퍼런스 배열만들기
        //Lesson 인스턴스의 주소를 저장할 레퍼런스를 3개 만든다 
       arr =new Lesson[3];  //   [배열주소]====> [0번 레퍼런스][1번 레퍼런스][2번 레퍼런스 ]
       //Lesson 인스턴스를 3개 만든것이아닌다 
       //arr가 가르키는 배열에서 0번 레퍼런스 변수에 Lesson 인스턴스의 주소를 저장하지않고 사용하면?
       //=> arr 가르키는 배열에서 0번변수에는 아무런 주소가 들어있지않다
       //=> 레퍼런스 배열의 각변수에 인스턴스 주소를 넣지않고 사용하면 null pointer exception 발생!
       
       //System.out.println(arr[0].no);
       //arr[0].no=100; 븅진짓;
       arr[0]=new Lesson();
       arr[1]=new Lesson();
       arr[2]=new Lesson();
       System.out.println(arr[0].no);
       arr[0].no=100;
       arr[0].title="자바";
       
       arr[1].no=200;
       arr[1].title="웹";
       
       System.out.println(arr[0].title);
       
       int []arr2 = new int [3];
       arr2[0]=100;
       arr2[2]=300;
           
  }
}
