package com.eomcs.util;

public class ListTest {
public static void main(String[] args) {
  LinkedList<String> list = new LinkedList<>();
  ArrayList <String> list3 = new ArrayList<>();

  
  
  //클래스 레퍼런스
  //해당 클래스의 인스턴스 주소를 저잫알수있다
  //해당클래스의 서브클래스에 대해서도 인스턴스 주소를 저장할수있다 
  
  Object obj1 = new LinkedList<>();
  Object obj2 = new ArrayList<>();
  Object obj3 = new String ("hello");
  
  //인터페이스 래퍼런스
  // 해당인터페이스 에따라 작성한 클래스의 인스턴스 주소를 저장할수있따
  // 해당 인터페이스를 구현한 클래스의 인스턴스 주소를 저장할수있따
  // 해당 인터페이스를 구현한 객체를 저장할수있다
  // 해당 인터페이스의 객체를 저장할수있다
  // List 객체를 저장할수있다 .
  List<String>list5 = new LinkedList<>();
  List<String>list6 = new LinkedList<>();
  //List<String>list7 = new java.util.Date();//컴파일 오류 ..








}
}
