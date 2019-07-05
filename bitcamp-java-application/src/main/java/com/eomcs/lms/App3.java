package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  private static java.io.InputStream keyboard = System.in;

  public static void main(String[] args) {
    Scanner keyscan = new Scanner(keyboard);
    String []number = new String[100];
    String []content = new String[100];
    Date []writeDate = new Date [100];
    int []visitNum = new int [100];
    
    
    int i =0;
    
    
    while(true) {
    
    number[i] = getStringValue("번호는");
    content[i] = getStringValue("내용은");
    writeDate[i] = getDateValue("작성일은");
    visitNum[i] = getIntValue("조회수는");
    i++;
    System.out.println("y/n선택");
    String check = keyscan.nextLine();
    if(!check.equals("y")) {
      break;
    }
    }
    System.out.println();
    for(int i2 =0; i2<i ; i2++) {
    System.out.printf("%s ,%s,%s,%s \n",number[i2],content[i2],writeDate[i2],visitNum[i2]);
    
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
