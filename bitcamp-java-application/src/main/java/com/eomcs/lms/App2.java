package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  private static java.io.InputStream keyboard = System.in;

  public static void main(String[] args) {

    Scanner keyscan = new Scanner(keyboard);
    int num[] = new int[100];
    String name[] = new String[100];
    String email[] = new String[100];
    String photo[] = new String[100];
    String password[] = new String[100];
    String phoneNumber[] = new String[100];
    Date joinDate[] = new Date[100];
    int i = 0;
    while (true) {
      num[i] = getIntValue("번호는");
      name[i] = getStringValue("이름은");
      email[i] = getStringValue("이메일은");
      password[i] = getStringValue("비밀번호는");
      photo[i] = getStringValue("사진은");
      phoneNumber[i] = getStringValue("전화번호는");
      joinDate[i] = getDateValue("가입일은");
      i++;
      System.out.println("y/n을 선택 ");
      String check = keyscan.nextLine();
      if (!check.equals("y")) {
        break;
      }
    }
    System.out.println();
    for (int i2 = 0; i2 < i; i2++) {
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",num[i2], name[i2], email[i2], password[i2],
          photo[i2], phoneNumber[i2], joinDate[i2]);
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
