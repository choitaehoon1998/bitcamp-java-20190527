package com.eomcs.lms;

import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    Scanner keyscan = new Scanner(keyboard);
    
    System.out.print("번호는?");
    String number = keyscan.nextLine();
    
    System.out.print("내용은?");
    String content = keyscan.nextLine();
    
    System.out.print("작성일은?");
    String writeDate = keyscan.nextLine();
    
    System.out.print("조회수는?");
    String visitNum = keyscan.nextLine();
    
    System.out.println();
    
    System.out.println("번호: "+number);
    System.out.println("내용: "+content);
    System.out.println("작성일: "+writeDate);
    System.out.println("조회수: "+visitNum);
  }
}
