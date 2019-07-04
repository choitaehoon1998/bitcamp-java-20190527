package com.eomcs.lms;

import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {

    java.io.InputStream keyboard = System.in;
    Scanner keyscan = new Scanner(keyboard);
    
    System.out.print("번호는?");
    String num = keyscan.nextLine();
    
    System.out.print("이름은?");
    String name = keyscan.nextLine();
    
    System.out.print("이메일은?");
    String email = keyscan.nextLine();
    
    System.out.print("비밀번호는?");
    String password = keyscan.nextLine();
    
    System.out.print("사진은?");
    String photo = keyscan.nextLine();
    
    System.out.print("전화번호는?");
    String phoneNumber = keyscan.nextLine();
    
    System.out.print("가입일자는?");
    String joinDate = keyscan.nextLine();
    
    System.out.println();
    
    System.out.println("번호: " + num);
    System.out.println("이름: " +name);
    System.out.println("이메일: "+email);
    System.out.println("암호: "+password);
    System.out.println("사진: "+photo);
    System.out.println("전화: "+phoneNumber);
    System.out.println("가입일: "+joinDate);
  }
}
