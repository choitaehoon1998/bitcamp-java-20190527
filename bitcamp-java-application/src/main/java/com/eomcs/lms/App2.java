package com.eomcs.lms;

import java.util.Scanner;

public class App2 {
  private static java.io.InputStream keyboard = System.in;

  public static void main(String[] args) {

    Scanner keyscan = new Scanner(keyboard);

    int num = getIntValue("번호는");
    String name = getStringValue("이름은");  
    String email =getStringValue("이메일은");
    String password = getStringValue("비밀번호는");
    String photo = getStringValue("사진은");
    String phoneNumber =getStringValue("전화번호는");
    
    java.sql.Date joinDate =getDateValue("가입일은");

    System.out.println();

    System.out.println("번호: " + num);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + email);
    System.out.println("암호: " + password);
    System.out.println("사진: " + photo);
    System.out.println("전화: " + phoneNumber);
    System.out.println("가입일: " + joinDate);
  }

  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message + "?");
        Scanner keyscan = new Scanner(keyboard);
        String num = keyscan.nextLine();
        int a = Integer.parseInt(num);
        return a;
      } catch (NumberFormatException e) {
        System.out.println("숫자로 입력하세요 ");
      }
    }
  }
  private static String getStringValue(String message) {
    while (true) {
        System.out.print(message + "?");
        Scanner keyscan = new Scanner(keyboard);
        String num = keyscan.nextLine();
        return num;
      
    }
  }
  private static java.sql.Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message + "?");
        Scanner keyscan = new Scanner(keyboard);
        String num = keyscan.nextLine();
        return java.sql.Date.valueOf(num);
      } catch (java.lang.IllegalArgumentException e) {
        System.out.println("2019-05-06형태로 입력하세요 ");
      }
    }
  }
}
