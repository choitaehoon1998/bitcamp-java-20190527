package com.eomcs.lms;

import java.util.Scanner;

public class App3 {
  private static java.io.InputStream keyboard = System.in;

  public static void main(String[] args) {
    String number = getStringValue("번호는");
    String content = getStringValue("내용은");
    java.sql.Date writeDate = getDateValue("작성일은");
    int visitNum = getIntValue("조회수는");

    System.out.println();

    System.out.println("번호: " + number);
    System.out.println("내용: " + content);
    System.out.println("작성일: " + writeDate);
    System.out.println("조회수: " + visitNum);
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
