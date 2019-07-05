package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  private static java.io.InputStream keyboard = System.in;

  public static void main(String[] args) {

    Scanner keyscan = new Scanner(keyboard);
    Member [] list1 = new Member[100];
    
    
    
    int i = 0;
    for(i=0; i<list1.length; i++) {
      Member k= new Member();
      
      k.num = getIntValue("번호는");
      k.name = getStringValue("이름은");
      k.email = getStringValue("이메일은");
      k.password = getStringValue("비밀번호는");
      k.photo = getStringValue("사진은");
      k.phoneNumber = getStringValue("전화번호는");
      k.joinDate = getDateValue("가입일은");
      
      list1 [i]= k;
      System.out.println("y/n을 선택 ");
      String check = keyscan.nextLine();
      if (!check.equals("y")) {
        break;
      }
    }
    System.out.println();
    for (int i2 = 0; i2 <= i; i2++) {
          Member k = new Member();
          k = list1[i2];
      
      
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",k.num, k.name, k.email, k.password,
          k.photo, k.phoneNumber, k.joinDate);
    }
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
